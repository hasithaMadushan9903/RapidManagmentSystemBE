package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseWiseClassFeeDTO {
    private Course course;
    private ClassFeeCourse classFeeCourse;
}
