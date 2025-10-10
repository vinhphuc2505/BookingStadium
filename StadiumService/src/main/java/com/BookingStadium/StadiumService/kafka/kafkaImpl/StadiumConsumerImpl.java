package com.BookingStadium.StadiumService.kafka.kafkaImpl;

import com.BookingStadium.StadiumService.dto.request.PriceRequest;
import com.BookingStadium.StadiumService.dto.response.CalculatedPriceResponse;
import com.BookingStadium.StadiumService.entity.Stadium;
import com.BookingStadium.StadiumService.kafka.StadiumConsumer;
import com.BookingStadium.StadiumService.repository.StadiumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class StadiumConsumerImpl implements StadiumConsumer {
    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StadiumProducerImpl stadiumProducer;

    @Override
    @KafkaListener(topics = "booking.request.price", groupId = "stadium-service-group")
    public void handlePriceRequest(String message) {
        try {
            PriceRequest request = objectMapper.readValue(message, PriceRequest.class);

            log.warn("📩 Received Kafka message: {}", message);

            log.warn("📩 Object: {}", request.toString());

            Stadium stadium = stadiumRepository.findById
                    (request.getStadiumId()).orElseThrow(() -> new RuntimeException("Stadium not existed"));
            BigDecimal price = stadium.getPrice();
            BigDecimal totalHour = request.getTotalHour();
            BigDecimal totalPrice = price.multiply(totalHour);


            var messageResponse = CalculatedPriceResponse.builder()
                    .bookingDetailsId(request.getBookingDetailsId())
                    .bookingId(request.getBookingId())
                    .stadiumId(request.getStadiumId())
                    .totalHour(request.getTotalHour())
                    .totalPrice(totalPrice)
                    .build();

            stadiumProducer.sendPriceResponse(messageResponse);

        }catch (Exception e){
            log.error("[Kafka] Failed to send price request: {}", e.getMessage(), e);
        }
    }
}