package com.rapidattendencesystem.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "hall")
public class Hall {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hallName;
	private Boolean isAC;
	private int numberOfChairs;
	private int numberOfDesks;
	private int capacity;
	
	@OneToMany(mappedBy = "hall")
	@JsonIgnore
	private List<Course> course;
	
	private Boolean isActive;
	
	
}
