package com.BookingStadium.PaymentService.dto.request.momo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoMoRequest {
    private String partnerCode;

    private String accessKey;

    private String requestId;

    private Long amount;

    private String orderId;

    private String orderInfo;

    private String redirectUrl;

    private String ipnUrl;

    private String requestType; // "captureWallet"

    private String extraData; // ""

    private String lang; // "vi"

    private String signature;
}
