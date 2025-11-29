package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject,Integer> {
    List<Subject> findByIsActive(Boolean active);

    @Query(value = "CALL CheckSubjectDeleteAvailable(:subId)", nativeQuery = true )
    List<Object[]> checkSubjectDeleteAvailability(@Param("subId") int subId);
}
