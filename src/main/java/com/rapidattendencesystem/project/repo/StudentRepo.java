package com.rapidattendencesystem.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByIsActive(Boolean active);
    Student findStudentByScodeAndIsActive(String scode, Boolean isActive);

//    @Query("SELECT TOP 1 s FROM Student s WHERE s.isActive = :isActive AND s.scode = :scode AND ")
//    Student findByStudentAndStudentid(@Param("scode") int scode, @Param("isActive") int isActive, @Param("sid") int sid);

    @Query("SELECT s FROM Student s JOIN s.enrolment e JOIN e.enrolmentCourses ec JOIN ec.course c WHERE c.teacher.id = :teacherId AND ec.isActive = :isActive")
    List<Student> findAllByTeacherId(@Param("teacherId") int teacherId,@Param("isActive") Boolean isActive);

    @Query("SELECT s.email FROM Student s JOIN s.enrolment e JOIN e.enrolmentCourses ec JOIN ec.course c WHERE c.teacher.id = :teacherId")
    List<String> findEmailsByTeacherId(@Param("teacherId") int teacherId);
}
