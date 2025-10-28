package com.BookingStadium.PaymentService.mapper;

import com.BookingStadium.PaymentService.dto.request.payment.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.payment.UpdatePaymentRequest;
import com.BookingStadium.PaymentService.dto.response.PaymentMethodResponse;
import com.BookingStadium.PaymentService.entity.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T19:28:50+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class PaymentMethodMapperImpl implements PaymentMethodMapper {

    @Override
    public PaymentMethod toPaymentMethod(CreatePaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        PaymentMethod.PaymentMethodBuilder paymentMethod = PaymentMethod.builder();

        paymentMethod.paymentMethodName( request.getPaymentMethodName() );

        return paymentMethod.build();
    }

    @Override
    public PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod) {
        if ( paymentMethod == null ) {
            return null;
        }

        PaymentMethodResponse.PaymentMethodResponseBuilder paymentMethodResponse = PaymentMethodResponse.builder();

        paymentMethodResponse.paymentMethodId( paymentMethod.getPaymentMethodId() );
        paymentMethodResponse.paymentMethodName( paymentMethod.getPaymentMethodName() );

        return paymentMethodResponse.build();
    }

    @Override
    public List<PaymentMethodResponse> toPaymentMethodResponse(List<PaymentMethod> paymentMethods) {
        if ( paymentMethods == null ) {
            return null;
        }

        List<PaymentMethodResponse> list = new ArrayList<PaymentMethodResponse>( paymentMethods.size() );
        for ( PaymentMethod paymentMethod : paymentMethods ) {
            list.add( toPaymentMethodResponse( paymentMethod ) );
        }

        return list;
    }

    @Override
    public void updatePaymentMethod(PaymentMethod paymentMethod, UpdatePaymentRequest request) {
        if ( request == null ) {
            return;
        }

        paymentMethod.setPaymentMethodName( request.getPaymentMethodName() );
    }
}
