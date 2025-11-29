package com.rapidattendencesystem.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "grade")
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String code;
	
	@OneToMany(mappedBy = "grade")
	@JsonIgnore
	private List<Course> course;
	
	private Boolean isActive;

	@PostPersist
	public void generateGradecode() {
		this.code = "Gr" + String.format("%01d", this.id);
	}
}
