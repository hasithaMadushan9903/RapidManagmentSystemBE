package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ApprovingStatusDTO;
import com.rapidattendencesystem.project.entity.ApprovingStatus;
import com.rapidattendencesystem.project.repo.ApprovingStatusRepo;
import com.rapidattendencesystem.project.repo.AttendanceRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ApprovingStatusService {

    @Autowired
    private ApprovingStatusRepo approvingStatusRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ApprovingStatusDTO> getStatus(){
        try{
            List<ApprovingStatusDTO> as = modelMapper.map(approvingStatusRepo.findAll(), new TypeToken<List<ApprovingStatusDTO>>() {}.getType()) ;
            return as;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
}
