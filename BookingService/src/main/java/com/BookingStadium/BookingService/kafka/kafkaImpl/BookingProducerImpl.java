package com.BookingStadium.BookingService.kafka.kafkaImpl;

import com.BookingStadium.BookingService.dto.request.CreateBillRequest;
import com.BookingStadium.BookingService.dto.request.PriceRequest;
import com.BookingStadium.BookingService.dto.request.SendTotalPriceRequest;
import com.BookingStadium.BookingService.exception.AppException;
import com.BookingStadium.BookingService.exception.ErrorCode;
import com.BookingStadium.BookingService.kafka.BookingProducer;
import com.BookingStadium.BookingService.repository.BookingDetailsRepository;
import com.BookingStadium.BookingService.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;



@Service
@Slf4j
@RequiredArgsConstructor
public class BookingProducerImpl implements BookingProducer {

    private final BookingRepository bookingRepository;

    private final BookingDetailsRepository bookingDetailsRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ObjectMapper objectMapper;


    @Override
    public void sendPriceCalculationRequest(UUID bookingDetailsId, UUID bookingId, UUID stadiumId, BigDecimal totalHour) {
        String topic = "booking.request.price";
        try {
            var messageObject = new PriceRequest(bookingDetailsId, bookingId, stadiumId, totalHour);

            String jsonMessage = objectMapper.writeValueAsString(messageObject);

            kafkaTemplate.send(topic, bookingId.toString(), jsonMessage);

        }catch (Exception e){
            throw new AppException(ErrorCode.KAFKA_SEND_ERROR);
        }
    }

    @Override
    public void sendCreateBillRequest(UUID bookingId, UUID userId) {
        String topic = "create.bill.request";
        try {
            var messageObject = new CreateBillRequest(bookingId, userId);

            String jsonMessage = objectMapper.writeValueAsString(messageObject);

            kafkaTemplate.send(topic, bookingId.toString(), jsonMessage);

        }catch (Exception e){
            throw new AppException(ErrorCode.KAFKA_SEND_ERROR);
        }
    }

    @Override
    public void sendTotalPrice(UUID bookingId, BigDecimal finalPrice){
        String topic = "send.total.price";
        try {
            var messageObject = new SendTotalPriceRequest(bookingId, finalPrice);

            String jsonMessage = objectMapper.writeValueAsString(messageObject);

            kafkaTemplate.send(topic, bookingId.toString(), jsonMessage);

        }catch (Exception e){
            throw new AppException(ErrorCode.KAFKA_SEND_ERROR);
        }
    }
}
