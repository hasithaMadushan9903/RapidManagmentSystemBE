package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {

}
