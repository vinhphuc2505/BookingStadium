package com.BookingStadium.PaymentService.mapper;

import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.response.BillResponse;
import com.BookingStadium.PaymentService.entity.Bill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toBill(CreateBillRequest request);

    BillResponse toBillResponse(Bill bill);

    List<BillResponse> toBillResponse(List<Bill> bills);
}
