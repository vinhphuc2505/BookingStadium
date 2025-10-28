package com.BookingStadium.PaymentService.controller;


import com.BookingStadium.PaymentService.dto.request.payment.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.payment.UpdatePaymentRequest;
import com.BookingStadium.PaymentService.dto.response.ApiResponse;
import com.BookingStadium.PaymentService.dto.response.PaymentMethodResponse;
import com.BookingStadium.PaymentService.service.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/method")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public ApiResponse<PaymentMethodResponse> createPayment(@RequestBody @Valid CreatePaymentRequest request){
        return ApiResponse.<PaymentMethodResponse>builder()
                .code(1000)
                .result(paymentMethodService.createPayment(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PaymentMethodResponse>> getPayment(){
        return ApiResponse.<List<PaymentMethodResponse>>builder()
                .code(1000)
                .result(paymentMethodService.getPayment())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PaymentMethodResponse> updatePayment(@PathVariable("id") int id,
                                                            @RequestBody @Valid UpdatePaymentRequest request){
        return ApiResponse.<PaymentMethodResponse>builder()
                .code(1000)
                .result(paymentMethodService.updatePayment(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePayment(@PathVariable("id") int id){
        paymentMethodService.deletePayment(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Payment method has been deleted")
                .build();
    }
}





