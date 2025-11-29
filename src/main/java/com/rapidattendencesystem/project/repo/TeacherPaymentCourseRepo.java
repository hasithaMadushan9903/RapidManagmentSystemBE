package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.TeacherPaymentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherPaymentCourseRepo  extends JpaRepository<TeacherPaymentCourse, Integer> {
    @Query("SELECT tpc.month.id, SUM(tpc.amount) FROM TeacherPaymentCourse tpc WHERE tpc.teacherPayment.teacher.isActive = true AND FUNCTION('YEAR', tpc.teacherPayment.date) = :year GROUP BY tpc.month.id")
    List<Object[]> getTeacherPaymentByMonth(@Param("year") int year);
}
