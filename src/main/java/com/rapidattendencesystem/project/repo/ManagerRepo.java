package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.FrontDesk;
import com.rapidattendencesystem.project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
    Manager findManagerByMcode(String mcode);
    List<Manager> findByIsActiveOrderByIdDesc(Boolean isActive);
}
