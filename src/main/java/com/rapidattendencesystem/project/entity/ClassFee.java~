package com.rapidattendencesystem.project.entity;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

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
@Table(name = "classFee")
public class ClassFee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime date;
	private int year;
	
	@ManyToOne
	private Student student;
	
	@OneToMany(mappedBy = "classFee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClassFeeCourse> classFeeCourse;

	private String reciptNumber;

	@PostPersist
	public void generateScode() {
		this.reciptNumber = "R" + String.format("%05d", this.id);
	}
}
