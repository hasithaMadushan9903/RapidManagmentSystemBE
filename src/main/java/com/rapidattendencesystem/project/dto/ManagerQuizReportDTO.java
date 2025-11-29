package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagerQuizReportDTO {
    private String courseCode;
    private String courseName;
    private int quizCount;
    private int attemptCount;
    private int noOfAPasses;
    private int noOfBPasses;
    private int noOfCPasses;
    private int noOfSPasses;
    private int noOfFPasses;
}
