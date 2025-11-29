package com.rapidattendencesystem.project.dto;


import com.rapidattendencesystem.project.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDetailsDTO {
    private Boolean isLoginSuccess;
    private int id;
    private String usercode;
    private String fullName;
    private List<PrivilagesDTO> privilagesDTO;
    private StudentDTO student;
    private TeacherDTO teacher;
    private OtherEmployeeDTO otherEmployeeDTO;
    private String profilePictureName;
    private String birthday;
    private String gender;
    private String email;
    private LocalDateTime joinedDate;
}
