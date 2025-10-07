package com.BookingStadium.BookingService.service.serviceImpl;

import com.BookingStadium.BookingService.dto.request.details.CreateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.request.details.UpdateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.response.BookingDetailsResponse;
import com.BookingStadium.BookingService.entity.Booking;
import com.BookingStadium.BookingService.entity.BookingDetails;
import com.BookingStadium.BookingService.mapper.BookingDetailsMapper;
import com.BookingStadium.BookingService.repository.BookingDetailsRepository;
import com.BookingStadium.BookingService.repository.BookingRepository;
import com.BookingStadium.BookingService.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;
    @Autowired
    private BookingDetailsMapper bookingDetailsMapper;
    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public BookingDetailsResponse createBookingDetails(CreateBookingDetailsRequest request) {
        BookingDetails bookingDetails = bookingDetailsMapper.toBookingDetails(request);

        Booking booking = bookingRepository.findById(request
                .getBookingId()).orElseThrow(() -> new RuntimeException("Booking not existed"));

        BigDecimal totalHour = totalHour(bookingDetails.getStartTime(), bookingDetails.getEndTime());
        bookingDetails.setTotalHours(totalHour);
        bookingDetails.setPrice(totalHour);
        bookingDetails.setBooking(booking);

        BigDecimal totalPrice = booking.getTotalPrice().add(totalHour);

        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);

        return bookingDetailsMapper.toBookingDetails(bookingDetailsRepository.save(bookingDetails));
    }

    @Override
    public List<BookingDetailsResponse> getBookingDetailsByBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not existed"));

        return bookingDetailsMapper.toBookingDetails(bookingDetailsRepository.findAllByBooking(booking));
    }

    @Override
    public BookingDetailsResponse updateBookingDetails(UUID id, UpdateBookingDetailsRequest request) {
//        BookingDetails bookingDetails = bookingDetailsRepository
//                .findById(id).orElseThrow(() -> new RuntimeException("Booking details not existed"));
//
//        Booking booking = bookingRepository.findById()

        return null;
    }

    @Override
    public void deleteBookingDetails(UUID id) {

    }



    private BigDecimal totalHour(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Time is not null");
        }

        if (endTime.isBefore(startTime)) {
            endTime = endTime.plusHours(24);
        }

        long minutes = Duration.between(startTime, endTime).toMinutes();

        return BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    }

}
