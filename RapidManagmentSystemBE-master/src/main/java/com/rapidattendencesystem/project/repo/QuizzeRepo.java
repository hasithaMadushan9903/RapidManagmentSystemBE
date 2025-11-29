package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Question;
import com.rapidattendencesystem.project.entity.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizzeRepo extends JpaRepository<Quizzes, Integer> {

    List<Quizzes> findByIsActiveOrderByIdDesc(Boolean isActive);
    Quizzes findByIdOrderByIdDesc(int id);

    @Query("SELECT q FROM Quizzes q WHERE q.course.id = :courseId ORDER BY q.id DESC")
    List<Quizzes> findByCourses(@Param("courseId") int courseId);

    @Query(value = "CALL GetQuizIdsForStudentId(:studentId, :courseId)", nativeQuery = true )
    List<Object[]> GetQuizIdsForStudentId(@Param("studentId") int studentId ,@Param("courseId") int courseId);
}
