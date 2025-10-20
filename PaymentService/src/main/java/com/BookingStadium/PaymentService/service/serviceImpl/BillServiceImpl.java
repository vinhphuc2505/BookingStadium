package com.BookingStadium.PaymentService.service.serviceImpl;

import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.response.BillResponse;
import com.BookingStadium.PaymentService.entity.Bill;
import com.BookingStadium.PaymentService.mapper.BillMapper;
import com.BookingStadium.PaymentService.repository.BillRepository;
import com.BookingStadium.PaymentService.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;


    @Override
    public BillResponse createBill(CreateBillRequest request) {
        Bill bill = billMapper.toBill(request);

        return billMapper.toBillResponse(billRepository.save(bill));
    }





}
