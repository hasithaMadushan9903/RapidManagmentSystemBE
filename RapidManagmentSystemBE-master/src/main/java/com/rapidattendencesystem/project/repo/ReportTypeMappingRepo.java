package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.ReportTypeMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportTypeMappingRepo extends JpaRepository<ReportTypeMapping, Integer> {
    List<ReportTypeMapping> findByRole_Id(int roleId);
}
