package com.BookingStadium.BookingService.dto.response;


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
public class BookingResponse {
    private UUID bookingId;

    private UUID userId;

    private UUID locationId;

    private BigDecimal totalPrice;

    private BookingStatus bookingStatus = BookingStatus.PENDING;

    private LocalDateTime dateCreated = LocalDateTime.now();
}
