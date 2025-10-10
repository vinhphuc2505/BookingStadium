package com.BookingStadium.BookingService.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceRequest {
    private UUID bookingDetailsId;

    private UUID bookingId;

    private UUID stadiumId;

    private BigDecimal totalHour;
}
