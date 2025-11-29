package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepo extends JpaRepository<TimeSlot , Integer> {
}
