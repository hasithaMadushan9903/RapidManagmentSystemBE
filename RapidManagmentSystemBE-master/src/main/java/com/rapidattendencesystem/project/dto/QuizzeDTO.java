package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Question;
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
public class QuizzeDTO {

    private int id;
    private Course course;
    private String title;
    private String createdDateTime;
    private List<QuestionDTO> questions;
    private Boolean isActive;
    private Boolean isStarted;
    private Boolean isFinished;
    private String dueDateTime;
    private int quesionPerQuiz;
    private Boolean isStudentAttemp;
    private ScoreDTO scoreDTO;


}
