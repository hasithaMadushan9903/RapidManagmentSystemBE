package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findLeaveRequestByApprovingStatusAndIsActiveOrderByCreatedDateTimeDesc(ApprovingStatus approvingStatus, Boolean isActive);
    List<LeaveRequest> findByIsActiveOrderByCreatedDateTimeDesc(Boolean isActive);
    List<LeaveRequest> findByIsActive(Boolean isActive);
    List<LeaveRequest> findByIsActiveAndTeacher_TcodeOrderByIdDesc(Boolean isActive, String tcode);

}
