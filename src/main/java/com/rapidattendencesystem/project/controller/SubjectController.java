package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.DeleteAvailabilityDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.SubjectDTO;
import com.rapidattendencesystem.project.entity.Subject;
import com.rapidattendencesystem.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subctrl")
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ResponseDTO responseDTO;

    @PutMapping("/deletesubject")
    public ResponseEntity<ResponseDTO> deleteSubject(@RequestBody SubjectDTO subjectDTO){
        try{
            Subject sub = subjectService.updateSubject(subjectDTO);
            if(sub.getId() > 0){
                responseDTO.setContent(sub);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
            }else{
                responseDTO.setContent(subjectDTO);
                responseDTO.setMassage("not Deleted");
                responseDTO.setCode("01");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");

            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getdeleteavailability/{subId}")
    public ResponseEntity<ResponseDTO> checkGradeDeleteAvailability(@PathVariable int subId){
        try{
            DeleteAvailabilityDTO availability = subjectService.checkSubjectDeleteAvailability(subId);
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

    @PutMapping("/updatesubject")
    public ResponseEntity<ResponseDTO> updateSubject(@RequestBody SubjectDTO subjectDTO){
        try{
            Subject sub = subjectService.updateSubject(subjectDTO);
            if(sub.getId() > 0){
                responseDTO.setContent(sub);
                responseDTO.setMassage("Saved");
                responseDTO.setCode("00");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
            }else{
                responseDTO.setContent(subjectDTO);
                responseDTO.setMassage("not saved");
                responseDTO.setCode("01");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");

            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createsubject")
    public ResponseEntity<ResponseDTO> createSubject(@RequestBody SubjectDTO subjectDTO){
        try{
            Subject sub = subjectService.createSubject(subjectDTO);
            if(sub.getId() > 0){
                responseDTO.setContent(sub);
                responseDTO.setMassage("Saved");
                responseDTO.setCode("00");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
            }else{
                responseDTO.setContent(subjectDTO);
                responseDTO.setMassage("not saved");
                responseDTO.setCode("01");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");

            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getsubjects")
    public ResponseEntity<ResponseDTO> getSubjects(){
        try{
            List<SubjectDTO> sub = subjectService.getSubjects();
            if(!sub.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(sub);

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
