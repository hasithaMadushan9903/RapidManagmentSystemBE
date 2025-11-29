package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Teacher;
import com.rapidattendencesystem.project.entity.TeacherPaymentCourse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherPaymentDTO {
    private int id;
    private LocalDateTime date;
    private Teacher teacher;
    private String reciptNumber;
    private Boolean isActive;
    private List<TeacherPaymentCourse> teacherPaymentCourse;
}
