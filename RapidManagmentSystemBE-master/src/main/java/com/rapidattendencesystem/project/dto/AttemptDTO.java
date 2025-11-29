package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Quizzes;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.entity.StudentAnswer;
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
public class AttemptDTO {

    private int id;
    private QuizzeDTO quizzes;
    private StudentDTO student;
    private String attemptDate;
    private List<StudentAnswer> studentAnswers;
}
