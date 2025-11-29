package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade , Integer> {
    List<Grade> findByIsActiveOrderByIdDesc(Boolean active);

    @Query(value = "CALL CheckGradeDeleteAvailability(:gradeId)", nativeQuery = true )
    List<Object[]> checkGradeDeleteAvailability(@Param("gradeId") int gradeId);
}
