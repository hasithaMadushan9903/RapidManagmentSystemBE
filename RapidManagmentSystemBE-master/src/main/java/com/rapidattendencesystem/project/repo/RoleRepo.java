package com.rapidattendencesystem.project.repo;


import com.rapidattendencesystem.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    List<Role> findByIsActive(Boolean isActive);
    Role findRoleById(int id);
}
