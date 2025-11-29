package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Attempt;
import com.rapidattendencesystem.project.entity.Quizzes;
import com.rapidattendencesystem.project.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScoreDTO {

    private int id;
    private Attempt attempt;
    private Student student;
    private Quizzes quizzes;
    private int score;
    private int totalWeight;
}
