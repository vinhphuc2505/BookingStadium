package com.BookingStadium.PaymentService.mapper;

import com.BookingStadium.PaymentService.dto.request.payment.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.payment.UpdatePaymentRequest;
import com.BookingStadium.PaymentService.dto.response.PaymentMethodResponse;
import com.BookingStadium.PaymentService.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethod toPaymentMethod(CreatePaymentRequest request);

    PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod);

    List<PaymentMethodResponse> toPaymentMethodResponse(List<PaymentMethod> paymentMethods);

    void updatePaymentMethod(@MappingTarget PaymentMethod paymentMethod, UpdatePaymentRequest request);
}
