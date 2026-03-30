package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.ClassRecording;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRecordingRepo extends JpaRepository<ClassRecording, Integer> {
    List<ClassRecording> findByCourse_Id(int CourseId);
}
