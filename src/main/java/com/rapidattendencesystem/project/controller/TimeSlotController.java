package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.dto.TimeSlotDTO;
import com.rapidattendencesystem.project.service.TeacherService;
import com.rapidattendencesystem.project.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/timeslotctrl")
@CrossOrigin("*")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/gettimeslot")
    public ResponseEntity<ResponseDTO> getTimeSlot(){
        try{
            List<TimeSlotDTO> timeSlotDTOS = timeSlotService.getTimeSlot();
            if(!timeSlotDTOS.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(timeSlotDTOS);

                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Not Found Any Records");
                responseDTO.setContent(null);

                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);

            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
