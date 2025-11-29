package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceCountByMonthAndCourseDTO {
    private int courseId;
    private String courseName;
    private int monthId;
    private String monthName;
    private int studentCount;
}
