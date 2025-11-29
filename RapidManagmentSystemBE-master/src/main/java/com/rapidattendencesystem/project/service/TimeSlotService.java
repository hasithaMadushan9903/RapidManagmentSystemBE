package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.dto.TimeSlotDTO;
import com.rapidattendencesystem.project.repo.TimeSlotRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TimeSlotService {

    @Autowired
    private TimeSlotRepo timeSlotRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<TimeSlotDTO> getTimeSlot(){
        try {
            return modelMapper.map(timeSlotRepo.findAll() , new TypeToken<List<TimeSlotDTO>>() {}.getType());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
