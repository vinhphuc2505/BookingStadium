package com.BookingStadium.BookingService.dto.request.booking;

import com.BookingStadium.BookingService.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookingRequest {
    private BookingStatus bookingStatus;

    private LocalDateTime lastUpdated = LocalDateTime.now();
}
