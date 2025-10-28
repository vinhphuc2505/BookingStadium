package com.BookingStadium.PaymentService.dto.request.bill;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBillRequest {
    private int paymentMethodId;
}
