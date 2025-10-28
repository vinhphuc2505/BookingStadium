package com.BookingStadium.PaymentService.service;


import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.request.bill.UpdateBillRequest;
import com.BookingStadium.PaymentService.dto.response.BillResponse;

import java.util.List;
import java.util.UUID;

public interface BillService {
    BillResponse createBill(CreateBillRequest request);

    List<BillResponse> getBillByUser(UUID id);

    BillResponse updateBill(UUID billId, UpdateBillRequest request);

    void deleteBill(UUID id);
}
