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
public class EnrolmentDTO {
	
	private int id;
	private Student student;
	private List<EnrolmentCourseDTO> enrolmentCourses;
	private LocalDateTime date;
	
}
