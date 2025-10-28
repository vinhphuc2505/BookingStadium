package com.BookingStadium.PaymentService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MoMoResponse {
    private String partnerCode;

    private String requestId;

    private String orderId;

    private Long amount;

    private long responseTime;

    private String message;

    private int resultCode;

    private String payUrl;

    private String deeplink;

    private String qrCodeUrl;
}
