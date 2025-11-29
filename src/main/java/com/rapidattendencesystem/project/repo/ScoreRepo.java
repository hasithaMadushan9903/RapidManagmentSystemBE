package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Question;
import com.rapidattendencesystem.project.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Integer> {
    List<Score> findByStudent_IdAndQuizzes_Id(int studentId, int quizid);
}
