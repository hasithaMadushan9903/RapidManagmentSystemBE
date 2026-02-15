package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayherePaymentDTO {
    private int id;
    private String receiptNumber;
    private String status;
}
