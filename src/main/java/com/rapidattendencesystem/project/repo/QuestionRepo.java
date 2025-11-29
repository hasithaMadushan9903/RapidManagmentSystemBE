package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

}
