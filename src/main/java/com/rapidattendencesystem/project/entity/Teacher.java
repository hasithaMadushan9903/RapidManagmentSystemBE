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
//@ToString
@Table(name = "Teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String tcode;
	private String fullName;
	private String title;
	private String highestQulification;
	private String birthday;
	private Boolean isActive;
	private String contactNumber;
	private String email;
	private String gender;

	
	@OneToMany(mappedBy = "teacher")
	@JsonIgnore
	private List<Course> course;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id")
	private Role role;

	@PostPersist
	public void generateCode() {
		this.tcode = "T" + String.format("%05d", this.id);
	}

}
