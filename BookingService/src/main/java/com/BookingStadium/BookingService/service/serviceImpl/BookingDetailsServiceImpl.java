package com.BookingStadium.BookingService.service.serviceImpl;

import com.BookingStadium.BookingService.dto.request.details.CreateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.request.details.UpdateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.response.BookingDetailsResponse;
import com.BookingStadium.BookingService.entity.Booking;
import com.BookingStadium.BookingService.entity.BookingDetails;
import com.BookingStadium.BookingService.enums.BookingDetailsStatus;
import com.BookingStadium.BookingService.exception.AppException;
import com.BookingStadium.BookingService.exception.ErrorCode;
import com.BookingStadium.BookingService.kafka.BookingProducer;
import com.BookingStadium.BookingService.mapper.BookingDetailsMapper;
import com.BookingStadium.BookingService.repository.BookingDetailsRepository;
import com.BookingStadium.BookingService.repository.BookingRepository;
import com.BookingStadium.BookingService.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private BookingProducer bookingProducer;

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @Transactional
    public BookingDetailsResponse createBookingDetails(CreateBookingDetailsRequest request) {
        BookingDetails bookingDetails = bookingDetailsMapper.toBookingDetails(request);
        // Tìm booking theo bookingId
        Booking booking = bookingRepository.findById(request
                .getBookingId()).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        // Tính tổng số giờ và lưu vào bookingDetails
        BigDecimal totalHour = totalHour(bookingDetails.getStartTime(), bookingDetails.getEndTime());
        bookingDetails.setTotalHours(totalHour);

        // Thiết lập quan hệ giữa bookingDetails và booking
        bookingDetails.setBooking(booking);

        // Lưu bookingDetails vào database
        bookingDetailsRepository.save(bookingDetails);

        // Gửi yêu cầu tính giá qua Kafka
        bookingProducer.sendPriceCalculationRequest
                (bookingDetails.getBookingDetailsId(), request.getBookingId(), bookingDetails.getStadiumId(), totalHour);

        return bookingDetailsMapper.toBookingDetails(bookingDetails);
    }

    @Override
    public List<BookingDetailsResponse> getBookingDetailsByBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        return bookingDetailsMapper.toBookingDetails(bookingDetailsRepository.findAllByBooking(booking));
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @Transactional
    public BookingDetailsResponse updateBookingDetails(UUID id, UpdateBookingDetailsRequest request) {
        BookingDetails bookingDetails = bookingDetailsRepository
                .findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOKING_DETAILS_NOT_FOUND));

        Booking booking = bookingDetails.getBooking();

        // Trừ tổng tiền trong booking
        BigDecimal totalPriceUpdate = booking.getTotalPrice().subtract(bookingDetails.getPrice());

        booking.setTotalPrice(totalPriceUpdate);
        bookingRepository.save(booking);
        // Tính tổng thời gian mới sau khi update
        BigDecimal newTotalHour = totalHour(request.getStartTime(), request.getEndTime());

        bookingDetails.setTotalHours(newTotalHour);
        bookingDetails.setStatus(BookingDetailsStatus.RECALCULATING);
        bookingDetailsMapper.updateBookingDetails(bookingDetails, request);

        // Gọi đến kafka để tính lại giá
        bookingProducer.sendPriceCalculationRequest
                (id, booking.getBookingId(), request.getStadiumId(), newTotalHour);

        return bookingDetailsMapper.toBookingDetails(bookingDetailsRepository.save(bookingDetails));
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    @Transactional
    public void deleteBookingDetails(UUID id) {
        BookingDetails bookingDetails = bookingDetailsRepository
                .findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOKING_DETAILS_NOT_FOUND));
        Booking booking = bookingDetails.getBooking();
        // Trừ tổng tiền trong booking
        BigDecimal totalPriceUpdate = booking.getTotalPrice().subtract(bookingDetails.getPrice());
        booking.setTotalPrice(totalPriceUpdate);

        bookingRepository.save(booking);
        bookingDetailsRepository.deleteById(id);
    }



    private BigDecimal totalHour(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            throw new AppException(ErrorCode.TIME_INVALID);
        }

        if (endTime.isBefore(startTime)) {
            endTime = endTime.plusHours(24);
        }

        long minutes = Duration.between(startTime, endTime).toMinutes();

        return BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    }

}
