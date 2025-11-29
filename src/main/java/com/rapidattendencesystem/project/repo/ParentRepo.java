package com.rapidattendencesystem.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Parent;

public interface ParentRepo extends JpaRepository<Parent, Integer> {
	Parent findByNic(String nic);
}
