package com.rapidattendencesystem.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "classFee_course")
public class ClassFeeCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "classFee_id")
	@JsonIgnore
	private ClassFee classFee;
	
	private BigDecimal amount;
	private Boolean isAddmision;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "month_id")
	private Month month;
	
}
