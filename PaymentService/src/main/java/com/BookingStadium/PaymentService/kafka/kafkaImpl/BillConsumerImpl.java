package com.BookingStadium.PaymentService.kafka.kafkaImpl;


import com.BookingStadium.PaymentService.dto.request.ReceiveTotalPriceRequest;
import com.BookingStadium.PaymentService.dto.request.bill.CreateBillRequest;
import com.BookingStadium.PaymentService.entity.Bill;
import com.BookingStadium.PaymentService.kafka.BillConsumer;
import com.BookingStadium.PaymentService.repository.BillRepository;
import com.BookingStadium.PaymentService.service.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Slf4j
@Service
@RequiredArgsConstructor
public class BillConsumerImpl implements BillConsumer {
    private final ObjectMapper objectMapper;

    private final BillService billService;

    private final BillRepository billRepository;

    @Override
    @KafkaListener(topics = "create.bill.request", groupId = "payment-service-group")
    public void createBill(String message) {
        try{
            CreateBillRequest request = objectMapper.readValue(message, CreateBillRequest.class);

            billService.createBill(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @KafkaListener(topics = "send.total.price", groupId = "payment-service-group")
    public void receiveTotalPrice(String message){
        log.info(message);
        try{
            ReceiveTotalPriceRequest request = objectMapper.readValue(message, ReceiveTotalPriceRequest.class);

            Bill bill = billRepository.findByBookingId
                    (request.getBookingId()).orElseThrow(() -> new RuntimeException("Bill not existed"));

            BigDecimal finalPrice = request.getTotalPrice();
            bill.setFinalPrice(finalPrice);

            billRepository.save(bill);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}



















