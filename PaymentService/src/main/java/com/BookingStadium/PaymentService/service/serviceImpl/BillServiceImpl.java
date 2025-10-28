package com.BookingStadium.PaymentService.service.serviceImpl;

import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.dto.request.bill.UpdateBillRequest;
import com.BookingStadium.PaymentService.dto.response.BillResponse;
import com.BookingStadium.PaymentService.entity.Bill;
import com.BookingStadium.PaymentService.entity.PaymentMethod;
import com.BookingStadium.PaymentService.mapper.BillMapper;
import com.BookingStadium.PaymentService.repository.BillRepository;
import com.BookingStadium.PaymentService.repository.PaymentMethodRepository;
import com.BookingStadium.PaymentService.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public BillResponse createBill(CreateBillRequest request) {
        Bill bill = billMapper.toBill(request);

        return billMapper.toBillResponse(billRepository.save(bill));
    }

    @Override
    public List<BillResponse> getBillByUser(UUID id) {
        return billMapper.toBillResponse(billRepository.findAllByUserId(id));
    }

    @Override
    public BillResponse updateBill(UUID billId, UpdateBillRequest request) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not existed"));

        System.out.println("Type id: " + request.getPaymentMethodId());

        PaymentMethod paymentMethod = paymentMethodRepository.findById
                (request.getPaymentMethodId()).orElseThrow(() -> new RuntimeException("Payment method not existed"));
        bill.setPaymentMethod(paymentMethod);
        billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }

    @Override
    public void deleteBill(UUID id) {
        billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not existed"));
        billRepository.deleteById(id);
    }


}
