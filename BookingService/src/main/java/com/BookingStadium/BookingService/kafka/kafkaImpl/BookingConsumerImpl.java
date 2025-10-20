package com.BookingStadium.BookingService.kafka.kafkaImpl;


import com.BookingStadium.BookingService.dto.response.CalculatedPriceResponse;
import com.BookingStadium.BookingService.entity.Booking;
import com.BookingStadium.BookingService.entity.BookingDetails;
import com.BookingStadium.BookingService.enums.BookingDetailsStatus;
import com.BookingStadium.BookingService.exception.AppException;
import com.BookingStadium.BookingService.exception.ErrorCode;
import com.BookingStadium.BookingService.kafka.BookingConsumer;
import com.BookingStadium.BookingService.kafka.BookingProducer;
import com.BookingStadium.BookingService.repository.BookingDetailsRepository;
import com.BookingStadium.BookingService.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingConsumerImpl implements BookingConsumer {
    private final BookingRepository bookingRepository;

    private final BookingDetailsRepository bookingDetailsRepository;

    private final ObjectMapper objectMapper;

    private final BookingProducer bookingProducer;

    @Override
    @KafkaListener(topics = "booking.response.price", groupId = "booking-service-group")
    public void receivePriceResponse(String message) {
        try {

            CalculatedPriceResponse response = objectMapper.readValue(message, CalculatedPriceResponse.class);

            BookingDetails bookingDetails =bookingDetailsRepository.findById
                    (response.getBookingDetailsId()).orElseThrow(() -> new AppException(ErrorCode.BOOKING_DETAILS_NOT_FOUND));

            bookingDetails.setStatus(BookingDetailsStatus.COMPLETED);
            bookingDetails.setPrice(response.getTotalPrice());

            bookingDetailsRepository.save(bookingDetails);

            Booking booking = bookingRepository.findById
                    (response.getBookingId()).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

            BigDecimal sumPrice = booking.getTotalPrice().add(response.getTotalPrice());
            booking.setTotalPrice(sumPrice);

            // Thêm kafka update Bill
            bookingProducer.sendTotalPrice(booking.getBookingId(), booking.getTotalPrice());

            bookingRepository.save(booking);

        }catch (Exception e){
            throw new AppException(ErrorCode.KAFKA_RECEIVE_ERROR);
        }
    }

}
