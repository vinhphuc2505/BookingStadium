package com.BookingStadium.PaymentService.dto.request.bill;


import com.BookingStadium.PaymentService.enums.BillStatus;
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
public class CreateBillRequest {
    private UUID bookingId;

    private UUID userId;

    private BigDecimal finalPrice = BigDecimal.ZERO;

    private BillStatus status = BillStatus.UNPAID;
}
