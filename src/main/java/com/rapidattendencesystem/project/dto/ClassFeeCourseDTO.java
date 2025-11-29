package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.ClassFee;
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
public class ClassFeeCourseDTO {

	private int id;
	private Course course;
	private ClassFee classFee;
	private int amount;
	private int isAddmision;
	private Month month;
}
