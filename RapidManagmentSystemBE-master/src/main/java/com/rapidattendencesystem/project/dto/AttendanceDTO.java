package com.rapidattendencesystem.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Month;
import com.rapidattendencesystem.project.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceDTO {
    private int id;
    private int year;
    private int date;
    private String attendDateTime;
    private Boolean isAttend;
    private Month month;
    private Student student;
    private Course course;
}
