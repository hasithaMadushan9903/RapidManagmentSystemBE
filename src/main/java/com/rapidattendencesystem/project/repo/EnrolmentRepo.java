package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Enrolment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrolmentRepo extends JpaRepository<Enrolment, Integer>{
    @Query("SELECT e FROM Enrolment e JOIN e.enrolmentCourses ec WHERE e.student.id = :sid AND ec.course.id = :cid AND ec.isActive = :isActive")
    List<Enrolment> findEnrolmentByStudentIdAndCourseId(@Param("sid") int sid, @Param("cid") int cid, @Param("isActive") Boolean isActive);
}
