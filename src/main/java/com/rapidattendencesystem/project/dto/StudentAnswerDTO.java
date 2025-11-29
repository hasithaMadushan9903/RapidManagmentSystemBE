package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Answers;
import com.rapidattendencesystem.project.entity.Attempt;
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
public class StudentAnswerDTO {

    private int id;
    private Attempt attempt;
    private Question question;
    private Answers answers;
}
