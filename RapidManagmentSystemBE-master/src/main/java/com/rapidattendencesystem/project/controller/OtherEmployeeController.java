package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.OtherEmployeeDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.service.HallService;
import com.rapidattendencesystem.project.service.OtherEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/otheremployee")
@CrossOrigin("*")
public class OtherEmployeeController {
    @Autowired
    private OtherEmployeeService otherEmployeeService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getotheremployees")
    public ResponseEntity<ResponseDTO> getOtherEmployees(){
        try{
            List<OtherEmployeeDTO> otherEmployeeDTOS = otherEmployeeService.getOtherEmployees();
            if(!otherEmployeeDTOS.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(otherEmployeeDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Halls Empty");
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

    @PutMapping("/updateordeleteemployee")
    public ResponseEntity<ResponseDTO> updateOrDeleteEmployee(@RequestBody OtherEmployeeDTO otherEmployeeDTO){
        try{
            OtherEmployeeDTO o1 = otherEmployeeService.updateOrDeleteEmployee(otherEmployeeDTO);
            if(o1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Employee Updated");
                responseDTO.setContent(o1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(otherEmployeeDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addemployee")
    public ResponseEntity<ResponseDTO> createemployee(@RequestBody OtherEmployeeDTO otherEmployeeDTO){
        try {
            OtherEmployeeDTO o1 = otherEmployeeService.addEmployee(otherEmployeeDTO);
            if(o1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Employee Created");
                responseDTO.setContent(o1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(otherEmployeeDTO);
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
