package com.BookingStadium.PaymentService.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MoMoConfig {

    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

    @Value("${momo.redirectUrl}")
    private String redirectUrl;

    @Value("${momo.ipnUrlNgrok}")
    private String ipnUrlNgrok;

    @Value("${momo.ipnUrl}")
    private String ipnUrl;

    @Value("${momo.endpoint}")
    private String endpoint;
}
