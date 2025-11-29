package com.rapidattendencesystem.project.repo;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rapidattendencesystem.project.entity.Course;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findByIsActive(Boolean active);
    List<Course> findByDateAndIsActive(String date, Boolean active);
    List<Course> findByIsActiveAndTeacher_Id(Boolean isActive, int teacherId);
    Boolean existsByIsActiveAndGrade_Id(Boolean isActive,int gradeId);
    Boolean existsByIsActiveAndTeacher_Id(Boolean isActive,int teacherId);
    Boolean existsByIsActiveAndSubject_Id(Boolean isActive,int subjectId);
    Boolean existsByIsActiveAndHall_Id(Boolean isActive,int hallId);

    @Query("SELECT c FROM Course c WHERE c.isActive = :isActive AND c.date = :date AND c.teacher.id = :teacherId")
    List<Course> findByDateAndIsActiveAndTeacherCode(@Param("isActive") Boolean isActive, @Param("date") String date, @Param("teacherId") int teacherId);

    @Query("SELECT c FROM Course c WHERE c.teacher.id = :teacherId AND c.isActive = true")
    List<Course> findAllByTeacherId(@Param("teacherId") int teacherId);

    @Query("SELECT c FROM Course c WHERE c.isActive = :isActive AND c.date = :date AND c.teacher.id IN (SELECT lr.teacher.id FROM LeaveRequest lr WHERE lr.approvingStatus.id = 1 AND lr.requestedDate LIKE CONCAT(:day, '%'))")
    List<Course> findCanceledCoursesByDate(@Param("isActive") Boolean isActive, @Param("day") String day, @Param("date") String date);

    @Query("SELECT  c FROM Course c WHERE c.isActive = :isActive AND c.id in (SELECT ec.course.id FROM Enrolment e JOIN e.enrolmentCourses ec WHERE e.student.id = :studentId )")
    List<Course> findCoursesByStudentId(@Param("studentId") int studentId, @Param("isActive") Boolean isActive);

    @Query("SELECT c FROM Course c WHERE c.isActive = :isActive AND c.date = :date AND c.id in (SELECT ec.course.id FROM Enrolment e JOIN e.enrolmentCourses ec WHERE e.student.id = :studentId )")
    List<Course> findCoursesByStudentIdAndDate(@Param("studentId") int studentId, @Param("date") String date, @Param("isActive") Boolean isActive);
}
