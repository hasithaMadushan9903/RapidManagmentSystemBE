package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Student;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentWiseCourseDTO {
    private Student student;
    private List<Course> courses;
}
