package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepo extends JpaRepository<Month,Integer> {
}
