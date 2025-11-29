package com.rapidattendencesystem.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String requestedDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "approving_status_id")
    private ApprovingStatus approvingStatus;

    private String officerUserCode;
    private LocalDateTime createdDateTime;

    private String leaveReason;

    private Boolean isActive;
}
