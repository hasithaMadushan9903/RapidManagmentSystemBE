package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassRecordingDTO {
    private int id;
    private Course course;
    private String heldDate;
    private String classRecUrl;
    private String createDateTime;
    private String createdUserCode;
    private Boolean isActive;
}
