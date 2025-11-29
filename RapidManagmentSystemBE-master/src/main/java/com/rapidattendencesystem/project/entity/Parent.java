package com.rapidattendencesystem.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "parent")
public class Parent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private String title;
	private String nic;
	private String birthday;
	private String relationship;
	private String occupation;
	private String contactNumber;
	private String email;

	
//	@OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
//	@JsonIgnore
//	private List<Student> students;
	
	private Boolean isActive;
	
}
