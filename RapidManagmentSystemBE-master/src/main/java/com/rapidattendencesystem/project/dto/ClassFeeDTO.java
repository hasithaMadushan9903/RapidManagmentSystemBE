package com.rapidattendencesystem.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.rapidattendencesystem.project.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassFeeDTO {

	private int id;
	private LocalDateTime date;
	private Student student;
	private int year;
	private Boolean isActive;
	private List<ClassFeeCourseDTO> classFeeCourse;
	private String reciptNumber;
}
