package com.BookingStadium.PaymentService.service;


import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.response.BillResponse;

public interface BillService {
    BillResponse createBill(CreateBillRequest request);
}
