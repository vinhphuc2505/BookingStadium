package com.BookingStadium.BookingService.dto.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDetailsResponse {
    private UUID bookingDetailsId;

    private BookingResponse booking;

    private UUID stadiumId;

    private LocalDate dateOfBooking;

    private LocalTime startTime;

    private LocalTime endTime;

    private BigDecimal totalHours;

    private BigDecimal price;
}
