package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.TeacherPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherPaymentRepo  extends JpaRepository<TeacherPayment, Integer> {

    List<TeacherPayment> findByTeacher_IdOrderByIdDesc(int teacherId);
}
