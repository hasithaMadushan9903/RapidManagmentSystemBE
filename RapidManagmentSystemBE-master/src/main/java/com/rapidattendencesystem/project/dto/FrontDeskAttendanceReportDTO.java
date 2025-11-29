package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FrontDeskAttendanceReportDTO {
    private String studnetName;
    private String studentCode;
    private String courseCode;
    private String courseName;
    private String reciptNumber;
    private BigDecimal paidAmount;
    private String payementCategory;
    private String payedDate;
}
