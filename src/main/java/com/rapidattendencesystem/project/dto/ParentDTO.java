package com.rapidattendencesystem.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParentDTO {
	private int id;
	private String fullName;
	private String title;
	private String nic;
	private String birthday;
	private String relationship;
	private String occupation;
	private String contactNumber;
	private Boolean isActive;
	private String email;
}
