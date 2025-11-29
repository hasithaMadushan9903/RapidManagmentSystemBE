package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Question;
import com.rapidattendencesystem.project.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnswerRepo extends JpaRepository<StudentAnswer, Integer> {
}
