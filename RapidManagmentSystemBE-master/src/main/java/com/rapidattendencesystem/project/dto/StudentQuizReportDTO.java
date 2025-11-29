package com.rapidattendencesystem.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentQuizReportDTO {
    private String quizName;
    private String courseCode;
    private String courseName;
    private String attemptDate;
    private int marks;
    private int noOfQuestionInQuiz;
}
