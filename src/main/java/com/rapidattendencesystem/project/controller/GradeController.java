package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.DeleteAvailabilityDTO;
import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Grade;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gradectrl")
@CrossOrigin("*") //allow to access this controller from outside
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getgrades")
    public ResponseEntity<ResponseDTO> getGrades(){
        try{
            List<GradeDTO> grades = gradeService.getGrades();
            if(!grades.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(grades);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Grades Empty");
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

    @GetMapping("/getdeleteavailability/{gradeId}")
    public ResponseEntity<ResponseDTO> checkGradeDeleteAvailability(@PathVariable int gradeId){
        try{
            DeleteAvailabilityDTO availability = gradeService.checkGradeDeleteAvailability(gradeId);
            if(availability != null){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(availability);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Grades Empty");
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
    @PutMapping("/deletegrade")
    public ResponseEntity<ResponseDTO> deleteGrade(@RequestBody GradeDTO gradeDTO){
        try{
            Grade g1 = gradeService.deleteGrade(gradeDTO);
            if(g1.getId() > 0){
                responseDTO.setContent(g1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(gradeDTO);
                responseDTO.setMassage("Error Occured");
                responseDTO.setCode("01");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updategrade")
    public ResponseEntity<ResponseDTO> updateGrade(@RequestBody GradeDTO gradeDTO){
        try{
            Grade g1 = gradeService.updateGrade(gradeDTO);
            if(g1.getId() > 0){
                responseDTO.setContent(g1);
                responseDTO.setMassage("Updated");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(gradeDTO);
                responseDTO.setMassage("Error Occured");
                responseDTO.setCode("01");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addgrade")
    public ResponseEntity<ResponseDTO> createGrade(@RequestBody GradeDTO gradeDTO){
        try {
            Grade G1 = gradeService.createGrade(gradeDTO);
            if(G1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Hall Inserted");
                responseDTO.setContent(G1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(gradeDTO);
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
