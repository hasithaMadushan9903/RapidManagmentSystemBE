package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ADAccountDTO {
    private int id;

    private LocalDateTime createdDateTime;
    private String userCode;
    private String passWord;
    private String profilePictureName;

}
