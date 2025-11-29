package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemAdminRepo extends JpaRepository<SystemAdmin, Integer> {
    SystemAdmin findSystemAdminBySyscode(String syscode);
    List<SystemAdmin> findByIsActiveOrderByIdDesc(Boolean isActive);
}
