package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ADAccountDTO;
import com.rapidattendencesystem.project.dto.FrontDeskAttendanceReportDTO;
import com.rapidattendencesystem.project.dto.LoginDetailsDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.ADAccount;
import com.rapidattendencesystem.project.service.ADAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/adaccountctrl")
@CrossOrigin("*")
public class ADAccountController {

    @Autowired
    private ADAccountService adAccountService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/updateprofilepicture/{profilePictureName}/{userCode}")
    public ResponseEntity<ResponseDTO> updateProfilePicture(@PathVariable String profilePictureName, @PathVariable String userCode){
        try {
            ADAccount rd = adAccountService.updateProfilePicture(profilePictureName, userCode);
            if(rd != null){
                responseDTO.setCode("00");
                responseDTO.setMassage("Data Updated");
                responseDTO.setContent(rd);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Update Failed");
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

    @PostMapping("/createuseraccount")
    public ResponseEntity<ResponseDTO> createUserAccount(@RequestBody ADAccountDTO adAccountDTO){
        try {
            System.out.println(adAccountDTO);
            ADAccount A1 = adAccountService.createUserAccount(adAccountDTO);
            System.out.println(A1);
            if(A1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Account Created");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Error Occered");
                responseDTO.setContent(adAccountDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateadacoount")
    public ResponseEntity<ResponseDTO> updateADAccount(@RequestBody ADAccountDTO adAccountDTO){
        try {
            System.out.println(adAccountDTO);
            ADAccount A1 = adAccountService.updateADAccount(adAccountDTO);
            System.out.println(A1);
            if(A1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Account Updated");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Error Occered");
                responseDTO.setContent(adAccountDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/getalogin")
    public ResponseEntity<ResponseDTO> getALogin(@RequestBody ADAccountDTO adAccountDTO){
        try {
            System.out.println(adAccountDTO);
            ADAccount A1 = adAccountService.getALogin(adAccountDTO);
            System.out.println(A1);
            if(A1.getId() > 0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Account Varified");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Error Occered");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/checklogin")
    public ResponseEntity<ResponseDTO> checklogin(@RequestBody ADAccountDTO adAccountDTO){
        try {
            System.out.println(adAccountDTO);
            LoginDetailsDTO A1 = adAccountService.checkLogin(adAccountDTO);
            System.out.println(A1);
            if(!(A1==null)){
                responseDTO.setCode("00");
                responseDTO.setMassage("Account Varified");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Error Occered");
                responseDTO.setContent(adAccountDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
