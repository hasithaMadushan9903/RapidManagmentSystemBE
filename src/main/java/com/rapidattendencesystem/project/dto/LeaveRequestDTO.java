package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.ApprovingStatus;
import com.rapidattendencesystem.project.entity.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveRequestDTO {
    private int id;
    private Teacher teacher;
    private String requestedDate;
    private ApprovingStatus approvingStatus;
    private String officerUserCode;
    private LocalDateTime createdDateTime;
    private String leaveReason;
    private Boolean isActive;
}
