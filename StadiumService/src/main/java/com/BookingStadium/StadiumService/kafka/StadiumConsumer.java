package com.BookingStadium.StadiumService.kafka;

public interface StadiumConsumer {
    void handlePriceRequest(String message);
}
