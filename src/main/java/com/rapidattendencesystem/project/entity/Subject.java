package com.rapidattendencesystem.project.entity;

import java.time.Year;
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
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@OneToMany(mappedBy = "subject")
	@JsonIgnore
	private List<Course> course;
	
	private Boolean isActive;

	private String code;
	private int firstTermModuleCount;
	private int secondTermModuleCount;
	private int thirdTermModuleCount;

	@PostPersist
	public void generateSubcode() {
		this.code = "SUB" + String.format("%01d", this.id);
	}
}
