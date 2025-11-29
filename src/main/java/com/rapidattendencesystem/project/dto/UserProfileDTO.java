package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Student;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserProfileDTO {

    private int id;
    private String name;
    private String imagePath;
    private Student student;
}
