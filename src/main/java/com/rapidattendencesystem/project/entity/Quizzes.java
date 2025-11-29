package com.rapidattendencesystem.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "questions")
@Table(name = "quizzes")
public class Quizzes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;
    private String createdDateTime;

    @OneToMany(mappedBy = "quizzes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    private Boolean isActive;
    private Boolean isStarted;
    private Boolean isFinished;
    private String dueDateTime;
    private int quesionPerQuiz;

}
