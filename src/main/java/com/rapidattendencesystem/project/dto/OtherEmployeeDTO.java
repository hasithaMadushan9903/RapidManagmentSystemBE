package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OtherEmployeeDTO {

    private int id;
    private int roleId;
    private String mCode;
    private String fCode;
    private String syscode;
    private String fullName;
    private String title;
    private String contactNumber;
    private String email;
    private Boolean isActive;
    private String birthDay;
    private String gender;
}
