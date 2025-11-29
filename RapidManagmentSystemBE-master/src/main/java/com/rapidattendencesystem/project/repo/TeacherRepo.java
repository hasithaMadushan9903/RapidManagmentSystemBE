package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher , Integer> {
    List<Teacher> findByIsActive(Boolean active);



    Optional<Teacher> findTeacherByTcode(String tcode);

    @Query(value = "CALL FindCourseWiseTeacherPayment(:teacherId, :monthId, :year)", nativeQuery = true )
    List<Object[]> getTeacherEarningsForMonthByCourseWise(@Param("teacherId") int teacherId, @Param("monthId") int monthId, @Param("year") int year );

}
