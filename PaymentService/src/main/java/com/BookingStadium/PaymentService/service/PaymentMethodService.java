package com.BookingStadium.PaymentService.service;


import com.BookingStadium.PaymentService.dto.request.payment.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.payment.UpdatePaymentRequest;
import com.BookingStadium.PaymentService.dto.response.PaymentMethodResponse;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodResponse createPayment(CreatePaymentRequest request);

    List<PaymentMethodResponse> getPayment();

    PaymentMethodResponse updatePayment(int id, UpdatePaymentRequest request);

    void deletePayment(int id);
}
