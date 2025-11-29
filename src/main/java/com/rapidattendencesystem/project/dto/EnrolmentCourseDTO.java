package com.rapidattendencesystem.project.dto;

import org.springframework.stereotype.Component;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Enrolment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnrolmentCourseDTO {

	private int id;
	private Boolean isActive;
	private Enrolment enrolment;
	private Course course;
}
