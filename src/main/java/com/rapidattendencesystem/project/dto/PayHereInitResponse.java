package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayHereInitResponse {
    private String merchantId;
    private String orderId;
    private String amount;
    private String currency;
    private String hash;
}
