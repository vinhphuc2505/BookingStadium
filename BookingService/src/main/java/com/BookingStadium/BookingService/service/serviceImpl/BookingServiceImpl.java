package com.BookingStadium.BookingService.service.serviceImpl;

import com.BookingStadium.BookingService.dto.request.booking.CreateBookingRequest;
import com.BookingStadium.BookingService.dto.request.booking.UpdateBookingRequest;
import com.BookingStadium.BookingService.dto.response.BookingResponse;
import com.BookingStadium.BookingService.entity.Booking;
import com.BookingStadium.BookingService.exception.AppException;
import com.BookingStadium.BookingService.exception.ErrorCode;
import com.BookingStadium.BookingService.kafka.BookingProducer;
import com.BookingStadium.BookingService.mapper.BookingMapper;
import com.BookingStadium.BookingService.repository.BookingRepository;
import com.BookingStadium.BookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private BookingProducer bookingProducer;



    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {
        Booking booking = bookingMapper.toBooking(request);

        bookingRepository.save(booking);

        bookingProducer.sendCreateBillRequest(booking.getBookingId(), booking.getUserId());

        return bookingMapper.toBookingResponse(booking);
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public List<BookingResponse> getBookingByUser(UUID id) {
        return bookingMapper.toBookingResponse(bookingRepository.findAllByUserId(id));
    }

    @Override
    @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public List<BookingResponse> getBookingByLocation(UUID id) {
        return bookingMapper.toBookingResponse(bookingRepository.findAllByLocationId(id));
    }


    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @Transactional
    public BookingResponse updateBooking(UUID bookingId, UpdateBookingRequest request) {
        var userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        Booking booking = bookingRepository
                .findByBookingIdAndUserId(bookingId, userId).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        bookingMapper.updateBooking(booking, request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @Transactional
    public void deleteBooking(UUID bookingId) {
        var userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        bookingRepository.findByBookingIdAndUserId(bookingId, userId).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        bookingRepository.deleteById(bookingId);
    }
}
