package com.BookingStadium.BookingService.kafka;




import java.math.BigDecimal;
import java.util.UUID;



public interface BookingProducer {
    void sendPriceCalculationRequest(UUID bookingDetailsId, UUID bookingId, UUID stadiumId, BigDecimal totalHour);

}
