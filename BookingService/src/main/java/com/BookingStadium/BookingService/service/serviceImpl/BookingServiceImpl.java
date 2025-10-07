package com.BookingStadium.BookingService.service.serviceImpl;

import com.BookingStadium.BookingService.dto.request.booking.CreateBookingRequest;
import com.BookingStadium.BookingService.dto.request.booking.UpdateBookingRequest;
import com.BookingStadium.BookingService.dto.response.BookingResponse;
import com.BookingStadium.BookingService.entity.Booking;
import com.BookingStadium.BookingService.mapper.BookingMapper;
import com.BookingStadium.BookingService.repository.BookingRepository;
import com.BookingStadium.BookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingMapper bookingMapper;



    @Override
    public BookingResponse createBooking(CreateBookingRequest request) {
        Booking booking = bookingMapper.toBooking(request);

        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public List<BookingResponse> getBookingByUser(UUID id) {
        return bookingMapper.toBookingResponse(bookingRepository.findAllByUserId(id));
    }


    @Override
    public BookingResponse updateBooking(UUID id, UpdateBookingRequest request) {
        Booking booking = bookingRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Booking not existed"));
        bookingMapper.updateBooking(booking, request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public void deleteBooking(UUID id) {
        bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not existed"));
        bookingRepository.deleteById(id);
    }
}
