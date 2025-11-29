package com.rapidattendencesystem.project.dto;


import com.rapidattendencesystem.project.entity.ReportTypes;
import com.rapidattendencesystem.project.entity.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportTypeMappingDTO {

    private int id;
    private ReportTypes reportTypes;
    private Role role;
}
