package com.rapidattendencesystem.project.entity;

import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
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
@Table(name = "students")
public class Student {

	//    primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String scode;
	private String fullName;
	private String callingName;
	private String birthDay;
	private String gender;
	private Boolean isActive;
	private String contactNumber;
	private String School;
	private Boolean isAdmisionPaid;
	private String email;

	@OneToMany(mappedBy = "student")
	@JsonIgnore
	private List<Enrolment> enrolment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	 
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "parent_id" , insertable = true)
	private Parent parent;
	
	@OneToMany(mappedBy = "student")
	@JsonIgnore
	private List<ClassFee> classFee;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@PostPersist
    public void generateScode() {
        this.scode = "S" + Integer.toString((Year.now().getValue())) + String.format("%05d", this.id);
    }
	 
} 
