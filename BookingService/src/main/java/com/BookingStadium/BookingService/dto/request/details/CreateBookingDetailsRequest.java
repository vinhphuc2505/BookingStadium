package com.BookingStadium.BookingService.dto.request.details;


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

}
