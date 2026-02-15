package com.rapidattendencesystem.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayHereConfig {
    @Value("${payhere.merchant-id}")
    private String merchantId;

    @Value("${payhere.merchant-secret}")
    private String merchantSecret;

    @Value("${payhere.payment-url}")
    private String paymentUrl;

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantSecret() {
        return merchantSecret;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }
}
