package com.BookingStadium.PaymentService.kafka;

import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;

public interface BillConsumer {
    void createBill(String message);

    void receiveTotalPrice(String message);
}
