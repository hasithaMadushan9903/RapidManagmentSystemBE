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
public class HallDTO {
    private int id;
    private String hallName;
    private Boolean isAC;
    private int numberOfChairs;
    private int numberOfDesks;
    private int capacity;
    private List<Course> course;
    private Boolean isActive;
}
