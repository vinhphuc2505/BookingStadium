package com.BookingStadium.BookingService.dto.request.booking;


import com.BookingStadium.BookingService.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingRequest {

    private UUID locationId;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private BookingStatus bookingStatus = BookingStatus.PENDING;

    private LocalDateTime dateCreated = LocalDateTime.now();

}
