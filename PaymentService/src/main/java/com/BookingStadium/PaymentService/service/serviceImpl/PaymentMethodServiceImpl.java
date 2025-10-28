package com.BookingStadium.PaymentService.service.serviceImpl;

import com.BookingStadium.PaymentService.dto.request.payment.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.payment.UpdatePaymentRequest;
import com.BookingStadium.PaymentService.dto.response.PaymentMethodResponse;
import com.BookingStadium.PaymentService.entity.PaymentMethod;
import com.BookingStadium.PaymentService.mapper.PaymentMethodMapper;
import com.BookingStadium.PaymentService.repository.PaymentMethodRepository;
import com.BookingStadium.PaymentService.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;


    @Override
    public PaymentMethodResponse createPayment(CreatePaymentRequest request) {
        PaymentMethod paymentMethod = paymentMethodMapper.toPaymentMethod(request);

        return paymentMethodMapper.toPaymentMethodResponse(paymentMethodRepository.save(paymentMethod));
    }

    @Override
    public List<PaymentMethodResponse> getPayment() {
        return paymentMethodMapper.toPaymentMethodResponse(paymentMethodRepository.findAll());
    }

    @Override
    public PaymentMethodResponse updatePayment(int id, UpdatePaymentRequest request) {
        PaymentMethod paymentMethod = paymentMethodRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Payment method not existed"));
        paymentMethodMapper.updatePaymentMethod(paymentMethod, request);
        return paymentMethodMapper.toPaymentMethodResponse(paymentMethodRepository.save(paymentMethod));
    }

    @Override
    public void deletePayment(int id) {
        paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not existed"));
        paymentMethodRepository.deleteById(id);
    }
}
