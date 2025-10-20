package com.BookingStadium.PaymentService.dto.response;


import com.BookingStadium.PaymentService.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillResponse {
    private UUID billId;

    private UUID bookingId;

    private UUID userId;

    private BigDecimal finalPrice;

    private BillStatus status;

    private LocalDateTime dateCreated;

    private LocalDateTime datePaid;
}
