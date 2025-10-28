package com.BookingStadium.PaymentService.controller;


import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.request.bill.UpdateBillRequest;
import com.BookingStadium.PaymentService.dto.response.ApiResponse;
import com.BookingStadium.PaymentService.dto.response.BillResponse;
import com.BookingStadium.PaymentService.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ApiResponse<List<BillResponse>> getBills(@PathVariable("id") UUID id){
        return ApiResponse.<List<BillResponse>>builder()
                .code(1000)
                .result(billService.getBillByUser(id))
                .build();
    }

    @PutMapping("/{billId}")
    public ApiResponse<BillResponse> updateBill(@PathVariable("billId") UUID billId, @RequestBody UpdateBillRequest request){
        return ApiResponse.<BillResponse>builder()
                .code(1000)
                .result(billService.updateBill(billId, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBill(@PathVariable("id") UUID id){
        billService.deleteBill(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Bill has been deleted")
                .build();
    }




}
