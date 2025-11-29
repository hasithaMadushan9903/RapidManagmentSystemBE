package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ApprovingStatusDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.LeaveRequestDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.LeaveRequest;
import com.rapidattendencesystem.project.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/leaverequest")
@CrossOrigin("*")
public class LeaveRequestController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/getleaverequestbyapprovingstatus")
    public ResponseEntity<ResponseDTO> getLeaveRequestByApprovingStatus(@RequestBody ApprovingStatusDTO approvingStatusDTO){
        try {
            List<LeaveRequestDTO> L1 = leaveRequestService.getLeaveRequestByApprovingStatus(approvingStatusDTO);
            if(!L1.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Request Found");
                responseDTO.setContent(L1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getleaverequest/{usercode}")
    public ResponseEntity<ResponseDTO> getLeaveRequest(@PathVariable String usercode){
        try {
            List<LeaveRequestDTO> L1 = leaveRequestService.getLeaveRequest(usercode);
            if(!L1.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Requests Found");
                responseDTO.setContent(L1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/makeleaverequest")
    public ResponseEntity<ResponseDTO> makeLeaveRequest(@RequestBody LeaveRequestDTO leaveRequestDTO){
        try {
            LeaveRequest L1 = leaveRequestService.createLeaveRequest(leaveRequestDTO);
            if(L1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Request Inserted");
                responseDTO.setContent(L1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(leaveRequestDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateordeleteleaverequest")
    public ResponseEntity<ResponseDTO> updateordeleteLeaveRequest(@RequestBody LeaveRequestDTO leaveRequestDTO){
        try {
            LeaveRequest L1 = leaveRequestService.updateOrDeleteLeaveRequest(leaveRequestDTO);
            if(L1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Request updated/deleted");
                responseDTO.setContent(L1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(leaveRequestDTO);
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
