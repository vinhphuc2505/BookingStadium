package com.BookingStadium.PaymentService.dto.request;


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
public class ReceiveTotalPriceRequest {
    private UUID bookingId;

    private BigDecimal totalPrice;
}
