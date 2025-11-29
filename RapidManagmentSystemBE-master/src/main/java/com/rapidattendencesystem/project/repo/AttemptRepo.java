package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Attempt;
import com.rapidattendencesystem.project.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttemptRepo extends JpaRepository<Attempt, Integer> {
    Boolean existsByQuizzes_IdAndStudent_Id(int quizzId,int studentId);

    @Query(value = "CALL GetStudentQuizReportDetails(:studentId)", nativeQuery = true )
    List<Object[]> GetStudentQuizReportDetails(@Param("studentId") int studentId);

    @Query(value = "CALL GetTeacherQuizReportData(:studentId)", nativeQuery = true )
    List<Object[]> GetTeacherQuizReportData(@Param("studentId") int studentId);

    @Query(value = "CALL GetManagerQuizReportDetails()", nativeQuery = true )
    List<Object[]> GetManagerQuizReportData();

    @Query(value = "CALL GetIncomeReportData(:year)", nativeQuery = true )
    List<Object[]> GetIncomeReportData(@Param("year") int year);

    @Query(value = "CALL GetExpencesReportData(:year)", nativeQuery = true )
    List<Object[]> GetExpencesReportData(@Param("year") int year);

    @Query(value = "CALL GetStudentAttendanceReportData(:studentId, :year)", nativeQuery = true )
    List<Object[]> GetStudentAttendanceReportData(@Param("studentId") int studentId, @Param("year") int year);

    @Query(value = "CALL GetIncomeReportDataForFrontdeskOfficer(:monthId, :courseId)", nativeQuery = true )
    List<Object[]> GetIncomeReportDataForFrontdeskOfficer(@Param("monthId") int monthId, @Param("courseId") int courseId);
}
