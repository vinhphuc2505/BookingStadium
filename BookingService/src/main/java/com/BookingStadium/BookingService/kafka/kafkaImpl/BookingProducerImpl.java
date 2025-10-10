package com.BookingStadium.BookingService.kafka.kafkaImpl;

import com.BookingStadium.BookingService.dto.request.PriceRequest;
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
            log.error("[Kafka] Failed to send price request: {}", e.getMessage(), e);
        }
    }
}
