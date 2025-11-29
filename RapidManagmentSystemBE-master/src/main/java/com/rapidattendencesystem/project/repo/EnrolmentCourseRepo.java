package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.EnrolmentCourse;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrolmentCourseRepo extends JpaRepository<EnrolmentCourse,Integer> {

    Boolean existsByIsActiveAndCourse_Id(Boolean isActive, int courseId);

    List<EnrolmentCourse> findByIsActive(Boolean active);
    List<EnrolmentCourse> findByEnrolment_Student(Student student);

    @Query("SELECT ec.course.id, COUNT(ec.enrolment.student) FROM EnrolmentCourse ec WHERE ec.enrolment.student.isActive = true GROUP BY ec.course.id")
    List<Object[]> getStudentCountByCourse();
}
