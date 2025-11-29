package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.AssignmentDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/assignmentctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/createassignment")
    public ResponseEntity<ResponseDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO){
        try{
            AssignmentDTO assignmentDTO1 = assignmentService.createAssignment(assignmentDTO);
            if(assignmentDTO1 != null){
                responseDTO.setContent(assignmentDTO1);
                responseDTO.setMassage("Assignment Created");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(null);
                responseDTO.setMassage("Bad Request");
                responseDTO.setCode("01");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
