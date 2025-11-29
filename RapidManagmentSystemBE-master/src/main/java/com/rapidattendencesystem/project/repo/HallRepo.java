package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepo extends JpaRepository<Hall, Integer> {
    List<Hall> findByIsActive(Boolean active);
}
