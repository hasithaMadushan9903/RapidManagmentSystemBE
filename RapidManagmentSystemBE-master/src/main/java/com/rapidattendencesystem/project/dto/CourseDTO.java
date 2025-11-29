package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Grade;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Subject;
import com.rapidattendencesystem.project.entity.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDTO {
	
	private int id;
	private Grade grade;
	private Subject subject;
	private Teacher teacher;
	private Hall hall;
	private String date;
	private String startTime;
	private String endTime;
	private Boolean isActive;
	private BigDecimal classFeeAmount;
	private String code;
}
