package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Answers;
import com.rapidattendencesystem.project.entity.Enrolment;
import com.rapidattendencesystem.project.entity.Question;
import com.rapidattendencesystem.project.entity.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswersRepo  extends JpaRepository<Answers, Integer> {
    Boolean existsByIdAndIsCorrect(int answerId, Boolean isCorrect);

    @Query("SELECT q FROM Answers a JOIN a.question q WHERE a.id = :answerId")
    Question findQuestionByAnswerId(@Param("answerId") int answerId);
}
