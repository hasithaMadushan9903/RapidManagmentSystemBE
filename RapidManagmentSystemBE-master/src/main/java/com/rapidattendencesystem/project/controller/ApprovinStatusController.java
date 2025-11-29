package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ApprovingStatusDTO;
import com.rapidattendencesystem.project.dto.AttendanceDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.ApprovingStatusService;
import com.rapidattendencesystem.project.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/approvinststusctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class ApprovinStatusController {


    @Autowired
    private ApprovingStatusService approvingStatusService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getapprovingstatuses")
    public ResponseEntity<ResponseDTO> getApprovingStatuses(){
        try{
            List<ApprovingStatusDTO> as = approvingStatusService.getStatus();
            if(!as.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(as);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
