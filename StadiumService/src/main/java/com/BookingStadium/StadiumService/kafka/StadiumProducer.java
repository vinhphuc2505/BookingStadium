package com.BookingStadium.StadiumService.kafka;

import com.BookingStadium.StadiumService.dto.response.CalculatedPriceResponse;

public interface StadiumProducer {
    void sendPriceResponse(CalculatedPriceResponse price);
}
