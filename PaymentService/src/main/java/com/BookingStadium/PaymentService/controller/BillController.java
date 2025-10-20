package com.BookingStadium.PaymentService.controller;


import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.response.ApiResponse;
import com.BookingStadium.PaymentService.dto.response.BillResponse;
import com.BookingStadium.PaymentService.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public ApiResponse<BillResponse> createBill(@RequestBody CreateBillRequest request){
        return ApiResponse.<BillResponse>builder()
                .code(1000)
                .result(billService.createBill(request))
                .build();
    }




}
