package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepo extends JpaRepository<Action, Integer> {
    List<Action> findByIsActive(Boolean isActive);
}
