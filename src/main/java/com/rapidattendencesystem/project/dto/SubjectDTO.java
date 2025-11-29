package com.rapidattendencesystem.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rapidattendencesystem.project.entity.Course;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDTO {

    private int id;
    private String code;
    private String name;
    private List<Course> course;
    private Boolean isActive;
    private int firstTermModuleCount;
    private int secondTermModuleCount;
    private int thirdTermModuleCount;
}
