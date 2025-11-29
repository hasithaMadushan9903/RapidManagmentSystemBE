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
public class CourseWiseMonthDTO {

    private Course course;
    private Month month;
}
