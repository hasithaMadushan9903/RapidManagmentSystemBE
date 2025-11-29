package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.AppIcon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppIconRepo extends JpaRepository<AppIcon , Integer> {
    List<AppIcon> findByIsActive(Boolean isActive);
}
