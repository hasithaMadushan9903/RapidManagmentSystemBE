package com.rapidattendencesystem.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherQuizReportDTO {
    private String quizName;
    private String courseCode;
    private String courseName;
    private int averageMarks;
    private int noOfAttempts;
}
