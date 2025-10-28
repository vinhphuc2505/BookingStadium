package com.BookingStadium.PaymentService.service;

import com.BookingStadium.PaymentService.dto.request.momo.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.momo.MomoIpnRequest;
import com.BookingStadium.PaymentService.dto.response.MoMoResponse;

public interface MoMoService {
    MoMoResponse createPayment(CreatePaymentRequest request);

    public void handleMomoIPN(MomoIpnRequest request);
}
