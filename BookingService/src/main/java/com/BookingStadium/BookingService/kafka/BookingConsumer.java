package com.BookingStadium.BookingService.kafka;



public interface BookingConsumer {

    void receivePriceResponse(String message);

}
