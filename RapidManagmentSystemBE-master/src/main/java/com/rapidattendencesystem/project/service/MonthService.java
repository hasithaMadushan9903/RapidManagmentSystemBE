package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.MonthDTO;
import com.rapidattendencesystem.project.repo.GradeRepo;
import com.rapidattendencesystem.project.repo.MonthRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class MonthService {

    @Autowired
    private MonthRepo monthRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<MonthDTO> getMonths(){
        try{
            return modelMapper.map(monthRepo.findAll(), new TypeToken<List<MonthDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
