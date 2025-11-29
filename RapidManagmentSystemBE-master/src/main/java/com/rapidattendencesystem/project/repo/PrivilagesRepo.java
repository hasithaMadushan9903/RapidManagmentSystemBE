package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Privilages;
import com.rapidattendencesystem.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilagesRepo extends JpaRepository<Privilages, Integer> {
    List<Privilages> findByIsActive(Boolean isActive);
    List<Privilages> findByRoleAndIsActive(Role role,Boolean isActive);
}
