package com.BookingStadium.BookingService.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CalculatedPriceResponse {
    private UUID bookingDetailsId;

    private UUID bookingId;

    private UUID stadiumId;

    private BigDecimal totalHour;

    private BigDecimal totalPrice;
}
