package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.StudentDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.repo.HallRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class HallService {

    @Autowired
    private HallRepo hallRepo;

    @Autowired
    private ModelMapper modelMapper;

//    the service layer for the update a hall
    public Hall updateHall(HallDTO hallDTO){
        try{
            Hall h1 = modelMapper.map(hallDTO , Hall.class);
            return hallRepo.save(h1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

//    the service layer for the update a hall
    public Hall deleteHall(HallDTO hallDTO){
        try{
            Hall h1 = modelMapper.map(hallDTO , Hall.class);
            return hallRepo.save(h1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

//    the service layer for the update a hall
    public List<HallDTO> getHalls(){
        try{
            return modelMapper.map(hallRepo.findByIsActive(true), new TypeToken<List<HallDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

//    the service layer for the update a hall
    public Hall createHall(HallDTO hallDTO){
        try {
            Hall H1 = modelMapper.map(hallDTO, Hall.class);
            if(hallRepo.existsById(H1.getId())){
                System.out.println("user exist");
                return H1;
            }else{
                Hall H2 = hallRepo.save(H1);
                System.out.println(H2);
                return H2;
            }
        }catch (Exception e){
            Hall H1 = modelMapper.map(hallDTO, Hall.class);
            return H1;
        }
    }


}
