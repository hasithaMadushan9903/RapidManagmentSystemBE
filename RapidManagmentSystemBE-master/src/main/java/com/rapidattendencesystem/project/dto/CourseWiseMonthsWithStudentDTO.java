package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseWiseMonthsWithStudentDTO {

    private List<CourseWiseMonthsDTO> payingCourseWiseMonths;
    private StudentDTO student;
    private int thisMonthId;
}
