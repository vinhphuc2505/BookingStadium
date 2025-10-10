package com.BookingStadium.BookingService.dto.request.details;


import com.BookingStadium.BookingService.enums.BookingDetailsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingDetailsRequest {
    private UUID bookingId;

    private UUID stadiumId;

    private LocalDate dateOfBooking;

    private LocalTime startTime;

    private LocalTime endTime;

    private BookingDetailsStatus status = BookingDetailsStatus.PENDING_CALCULATION;

}
