package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.ApprovingStatus;
import com.rapidattendencesystem.project.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovingStatusRepo extends JpaRepository<ApprovingStatus, Integer> {
}
