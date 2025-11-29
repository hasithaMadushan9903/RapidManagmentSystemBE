package com.rapidattendencesystem.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rapidattendencesystem.project.entity.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerDTO {

    private int id;
    private String description;
    @JsonIgnore
    private QuestionDTO question;
    private Boolean isCorrect;
}
