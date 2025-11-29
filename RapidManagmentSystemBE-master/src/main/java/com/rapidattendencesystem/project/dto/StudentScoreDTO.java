package com.rapidattendencesystem.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentScoreDTO {
    private int id;
    private AttemptDTO attempt;
    private QuizzeDTO quizzes;
    private StudentDTO student;

    private int totalScore;
    private int percentage;
    private int createdDateTime;
}
