package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Month;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseWiseMonthsDTO {

    private Course course;
    private String courseName;
    private BigDecimal courseAmount;
    private List<Month> months;
    private int studentCount;
}
