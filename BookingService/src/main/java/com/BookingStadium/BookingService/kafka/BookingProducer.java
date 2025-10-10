package com.BookingStadium.BookingService.kafkaService;


import java.math.BigDecimal;
import java.util.UUID;

public interface BookingProducer {
    void sendPriceCalculationRequest(UUID stadiumId, BigDecimal totalHour);
}
