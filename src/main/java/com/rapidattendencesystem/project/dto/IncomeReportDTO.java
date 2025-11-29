package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomeReportDTO {

    private String Descriptiopn;
    private int janAmount;
    private int febAmount;
    private int marAmount;
    private int aprAmount;
    private int mayAmount;
    private int junAmount;
    private int julAmount;
    private int augAmount;
    private int sepAmount;
    private int octAmount;
    private int novAmount;
    private int decAmount;
    private int total;

}
