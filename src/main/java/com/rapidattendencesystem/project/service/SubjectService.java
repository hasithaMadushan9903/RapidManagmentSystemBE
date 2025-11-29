package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.DeleteAvailabilityDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.SubjectDTO;
import com.rapidattendencesystem.project.entity.Subject;
import com.rapidattendencesystem.project.repo.SubjectRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Subject updateSubject(SubjectDTO subjectDTO){
        try {
            Subject sub = modelMapper.map(subjectDTO , Subject.class);
            if(subjectRepo.existsById(sub.getId())){
                Subject sub2 = subjectRepo.save(sub);
                System.out.println("Successfully Removed/Updated");
                return sub2;
            }else{
                System.out.println("User Not Exist");
                return sub;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DeleteAvailabilityDTO checkSubjectDeleteAvailability(int subId){
        try{
            DeleteAvailabilityDTO deleteAvailabilityDTO = new DeleteAvailabilityDTO();
            List<Object[]> deleteAvailabilityObj = subjectRepo.checkSubjectDeleteAvailability(subId);

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

    public Subject createSubject(SubjectDTO subjectDTO){
        try {
            Subject sub = modelMapper.map(subjectDTO , Subject.class);
            if(subjectRepo.existsById(sub.getId())){
                System.out.println("Already Added");
                return sub;
            }else{
                Subject sub2 = subjectRepo.save(sub);
                System.out.println("Successfully Saved");
                return sub2;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<SubjectDTO> getSubjects(){
        try {
            return modelMapper.map(subjectRepo.findByIsActive(true) , new TypeToken<List<SubjectDTO>>() {}.getType());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
