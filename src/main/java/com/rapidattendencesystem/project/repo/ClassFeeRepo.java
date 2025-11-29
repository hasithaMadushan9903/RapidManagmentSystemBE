package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Month;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.ClassFee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassFeeRepo extends JpaRepository<ClassFee, Integer> {
    Optional<ClassFee> findTop1ByStudentAndClassFeeCourse_CourseOrderByClassFeeCourse_MonthAsc(Student student, Course course);
    Optional<ClassFee> findTop1ByStudentAndClassFeeCourse_CourseOrderByClassFeeCourse_MonthDesc(Student student, Course course);

    @Query("SELECT cfc.month FROM ClassFee cf JOIN cf.classFeeCourse cfc WHERE cf.student = :student AND cf.isActive = :isActive AND cfc.course = :course")
    List<Month> findPayedMonthByStudentAndCourse(@Param("student") Student student, @Param("course") Course course, @Param("isActive") Boolean isActive);

    @Query("SELECT cfc.month FROM ClassFee cf JOIN cf.classFeeCourse cfc WHERE cf.student = :student AND cf.isActive = :isActive AND cfc.course = :course ORDER BY cfc.month ASC")
    List<Month> findTop1MonthByStudentAndCourseOrderByMonthAsc(@Param("student") Student student, @Param("course") Course course, @Param("isActive") Boolean isActive);

}
