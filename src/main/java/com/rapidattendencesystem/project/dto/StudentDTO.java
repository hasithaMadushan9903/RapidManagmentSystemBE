package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Address;
import com.rapidattendencesystem.project.entity.Parent;

import com.rapidattendencesystem.project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {	
	private int id;
	private String scode;
	private String fullName;
	private String callingName;
	private String birthDay;
	private String gender;
	private Boolean isActive;
	private String contactNumber;
	private Address address;
	private Parent parent;
	private String School;
	private Boolean isAdmisionPaid;
	private String email;
	private Role role;
}
