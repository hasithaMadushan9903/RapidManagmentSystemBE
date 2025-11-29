package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeDTO {
    private int id;
    private String name;
    private String code;
    private List<Course> course;

    private Boolean isActive;
}
