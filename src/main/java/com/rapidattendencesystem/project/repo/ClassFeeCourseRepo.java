package com.rapidattendencesystem.project.repo;


import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Month;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClassFeeCourseRepo extends JpaRepository<ClassFeeCourse, Integer> {
    ClassFeeCourse findTop1ByCourseAndClassFee_StudentOrderByMonthDesc(Course course, Student student);
    ClassFeeCourse findTop1ByCourseAndClassFee_StudentAndClassFee_IsActiveOrderByMonthAsc(Course course, Student student,Boolean isActive);

    @Query("SELECT cfc.classFee.student FROM ClassFeeCourse cfc WHERE cfc.course = :course AND cfc.month = :month AND cfc.classFee.date >= :start AND cfc.classFee.date < :end")
    List<Student> findClassFeeCourse_ClassFee_StudentByCourseAndMonth(@Param("course") Course course,@Param("month") Month month,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);

    @Query("SELECT cfc.month.id, SUM(cfc.amount) FROM ClassFeeCourse cfc WHERE cfc.classFee.student.isActive = true AND FUNCTION('YEAR', cfc.classFee.date) = :year GROUP BY cfc.month.id")
    List<Object[]> getStudentClassFeeByCourse(@Param("year") int year);


}
