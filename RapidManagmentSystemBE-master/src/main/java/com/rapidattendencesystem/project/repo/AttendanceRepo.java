package com.rapidattendencesystem.project.repo;


import com.rapidattendencesystem.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByCourseAndYearAndMonthAndDate(Course course, int year, Month month, int date);
    List<Attendance> findByCourseAndYearAndMonth(Course course, int year, Month month);
    int countByCourseAndYearAndMonthAndStudentAndIsAttend(Course course, int year, Month month, Student student, Boolean isAttend);

    Boolean existsByYearAndIsAttendAndMonth_IdAndStudent_IdAndCourse_Id(int year,Boolean isAttend, int monthId, int studentId, int courseId);
    @Query(value = "CALL GetAttendanceCountByMonthAndCourse(:year)", nativeQuery = true )
    List<Object[]> getAttendanceCountByCourseAndMonth(@Param("year") int year);

    int countByCourseAndStudentAndMonthAndIsAttendAndYear(Course course, Student student, Month month, Boolean isAttend, int year);
}
