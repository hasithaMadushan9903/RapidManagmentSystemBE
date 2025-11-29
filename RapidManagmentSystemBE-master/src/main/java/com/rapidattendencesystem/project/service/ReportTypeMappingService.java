package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ReportTypeMappingDTO;
import com.rapidattendencesystem.project.dto.SubjectDTO;
import com.rapidattendencesystem.project.entity.ReportTypeMapping;
import com.rapidattendencesystem.project.repo.QuizzeRepo;
import com.rapidattendencesystem.project.repo.ReportTypeMappingRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ReportTypeMappingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReportTypeMappingRepo reportTypeMappingRepo;

    public List<ReportTypeMappingDTO> getReportsUnderRole(int roleId){
        try{
            List<ReportTypeMapping> reportTypeMappings = reportTypeMappingRepo.findByRole_Id(roleId);
            List<ReportTypeMappingDTO> reportTypeMappingDTOS = modelMapper.map(reportTypeMappings , new TypeToken<List<ReportTypeMappingDTO>>() {}.getType());
            return reportTypeMappingDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
