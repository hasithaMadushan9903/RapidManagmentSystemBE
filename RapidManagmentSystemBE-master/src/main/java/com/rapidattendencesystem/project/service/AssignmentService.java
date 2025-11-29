package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.AssignmentDTO;
import com.rapidattendencesystem.project.entity.Assignment;
import com.rapidattendencesystem.project.repo.AssignmentRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public AssignmentDTO createAssignment(AssignmentDTO assignmentData ){
        try{
            Assignment assignment = modelMapper.map(assignmentData , Assignment.class);
            return modelMapper.map(assignmentRepo.save(assignment), AssignmentDTO.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
