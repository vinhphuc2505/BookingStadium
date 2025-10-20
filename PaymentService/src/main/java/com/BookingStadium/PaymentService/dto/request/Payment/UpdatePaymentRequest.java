package com.BookingStadium.PaymentService.dto.request.Payment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
    private String paymentMethodName;
}
