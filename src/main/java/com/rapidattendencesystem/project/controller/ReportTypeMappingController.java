package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ReportTypeMappingDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.ReportTypeMapping;
import com.rapidattendencesystem.project.service.QuizzService;
import com.rapidattendencesystem.project.service.ReportTypeMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reporttypemappingctrl")
@CrossOrigin("*")
public class ReportTypeMappingController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private ReportTypeMappingService reportTypeMappingService;

    @GetMapping("/getreportsunderrole/{roleId}")
    public ResponseEntity<ResponseDTO> getReportsUnderRole(@PathVariable int roleId){
        try{
            List<ReportTypeMappingDTO> reportTypeMappingDTOS = reportTypeMappingService.getReportsUnderRole(roleId);
            if(!reportTypeMappingDTOS.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Reports Found");
                responseDTO.setContent(reportTypeMappingDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Reports Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
