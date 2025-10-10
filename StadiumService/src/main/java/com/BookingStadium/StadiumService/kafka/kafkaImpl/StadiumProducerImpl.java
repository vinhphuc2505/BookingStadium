package com.BookingStadium.StadiumService.kafka.kafkaImpl;

import com.BookingStadium.StadiumService.dto.response.CalculatedPriceResponse;
import com.BookingStadium.StadiumService.kafka.StadiumProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StadiumProducerImpl implements StadiumProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void sendPriceResponse(CalculatedPriceResponse price) {
        try{
            String jsonResponse = objectMapper.writeValueAsString(price);

            kafkaTemplate.send("booking.response.price", price.getBookingDetailsId().toString(), jsonResponse);

        }catch (Exception e){
            log.error("[Kafka] Failed to send price request: {}", e.getMessage(), e);
        }

    }
}
