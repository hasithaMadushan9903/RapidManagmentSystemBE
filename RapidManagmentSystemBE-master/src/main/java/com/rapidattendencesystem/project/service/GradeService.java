package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.DeleteAvailabilityDTO;
import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.entity.Grade;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.repo.GradeRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<GradeDTO> getGrades(){
        try{
            return modelMapper.map(gradeRepo.findByIsActiveOrderByIdDesc(true), new TypeToken<List<GradeDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Grade createGrade(GradeDTO gradeDTO){
        try {
            Grade G1 = modelMapper.map(gradeDTO, Grade.class);
            if(gradeRepo.existsById(G1.getId())){
                System.out.println("user exist");
                return G1;
            }else{
                Grade G2 = gradeRepo.save(G1);
                System.out.println(G2);
                return G2;
            }
        }catch (Exception e){
            Grade G1 = modelMapper.map(gradeDTO, Grade.class);
            return G1;
        }
    }

    public Grade updateGrade(GradeDTO gradeDTO){
        try{
            Grade g1 = modelMapper.map(gradeDTO , Grade.class);
            return gradeRepo.save(g1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DeleteAvailabilityDTO checkGradeDeleteAvailability(int gradeId){
        try{
            DeleteAvailabilityDTO deleteAvailabilityDTO = new DeleteAvailabilityDTO();
            List<Object[]> deleteAvailabilityObj = gradeRepo.checkGradeDeleteAvailability(gradeId);

            for(Object[] obj : deleteAvailabilityObj){
                Boolean availability = (Boolean) obj[0];
                deleteAvailabilityDTO.setIsDeleteAvailable(availability);
            }

            return deleteAvailabilityDTO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Grade deleteGrade(GradeDTO gradeDTO){
        try{
            Grade g1 = modelMapper.map(gradeDTO , Grade.class);
            return gradeRepo.save(g1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
