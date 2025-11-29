package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Answers;
import com.rapidattendencesystem.project.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answers, Integer> {
}
