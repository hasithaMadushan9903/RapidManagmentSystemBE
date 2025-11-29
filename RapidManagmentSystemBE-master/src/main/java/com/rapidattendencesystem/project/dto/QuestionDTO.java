package com.rapidattendencesystem.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rapidattendencesystem.project.entity.Answers;
import com.rapidattendencesystem.project.entity.Assignment;
import com.rapidattendencesystem.project.entity.Quizzes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDTO {

    private int id;
    private String description;
    @JsonIgnore
    private QuizzeDTO quizzes;
    private List<AnswerDTO> answers;
    private int wight;
}
