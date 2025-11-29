package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseWisePaymentDTO {
    private int courseId;
    private int studentCount;
    private Long amount;
    private Boolean isPayed;
}
