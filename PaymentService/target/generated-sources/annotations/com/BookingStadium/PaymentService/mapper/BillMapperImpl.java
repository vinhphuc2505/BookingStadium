package com.BookingStadium.PaymentService.mapper;

import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.response.BillResponse;
import com.BookingStadium.PaymentService.entity.Bill;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T18:08:15+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class BillMapperImpl implements BillMapper {

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public Bill toBill(CreateBillRequest request) {
        if ( request == null ) {
            return null;
        }

        Bill.BillBuilder bill = Bill.builder();

        bill.bookingId( request.getBookingId() );
        bill.userId( request.getUserId() );
        bill.finalPrice( request.getFinalPrice() );
        bill.status( request.getStatus() );

        return bill.build();
    }

    @Override
    public BillResponse toBillResponse(Bill bill) {
        if ( bill == null ) {
            return null;
        }

        BillResponse.BillResponseBuilder billResponse = BillResponse.builder();

        billResponse.billId( bill.getBillId() );
        billResponse.paymentMethod( paymentMethodMapper.toPaymentMethodResponse( bill.getPaymentMethod() ) );
        billResponse.bookingId( bill.getBookingId() );
        billResponse.userId( bill.getUserId() );
        billResponse.finalPrice( bill.getFinalPrice() );
        billResponse.status( bill.getStatus() );
        billResponse.dateCreated( bill.getDateCreated() );
        billResponse.datePaid( bill.getDatePaid() );

        return billResponse.build();
    }

    @Override
    public List<BillResponse> toBillResponse(List<Bill> bills) {
        if ( bills == null ) {
            return null;
        }

        List<BillResponse> list = new ArrayList<BillResponse>( bills.size() );
        for ( Bill bill : bills ) {
            list.add( toBillResponse( bill ) );
        }

        return list;
    }
}
