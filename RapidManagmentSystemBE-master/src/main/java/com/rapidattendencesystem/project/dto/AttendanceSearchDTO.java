package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Month;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceSearchDTO {
    private CourseDTO course;
    private MonthDTO month;
    private int year;
    private int date;
    private StudentDTO student;
    private Boolean isAttend;
}
