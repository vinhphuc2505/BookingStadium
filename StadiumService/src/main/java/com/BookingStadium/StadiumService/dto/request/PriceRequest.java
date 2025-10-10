package com.BookingStadium.StadiumService.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {
    private UUID bookingDetailsId;

    private UUID bookingId;

    private UUID stadiumId;

    private BigDecimal totalHour;
}
