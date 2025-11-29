package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.PrivilagesDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Privilages;
import com.rapidattendencesystem.project.repo.PrivilagesRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class PrivilagesService {

    @Autowired
    private PrivilagesRepo privilagesRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<Privilages> createPrivilage(List<PrivilagesDTO> privilagesDTO){
        try {
            List<Privilages> p1 = modelMapper.map(privilagesDTO, new TypeToken<List<Privilages>>() {}.getType()) ;
            if(p1.isEmpty()){
                System.out.println("privilage exist");
                return null;
            }else{
                List<Privilages> p2 = privilagesRepo.saveAll(p1);
                return p2;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Privilages deletePrivilage(PrivilagesDTO privilagesDTO){
        try{
            Privilages p1 = modelMapper.map(privilagesDTO , Privilages.class);
            return privilagesRepo.save(p1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<PrivilagesDTO> getPrivilages(){
        try{
            return modelMapper.map(privilagesRepo.findByIsActive(true), new TypeToken<List<PrivilagesDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
