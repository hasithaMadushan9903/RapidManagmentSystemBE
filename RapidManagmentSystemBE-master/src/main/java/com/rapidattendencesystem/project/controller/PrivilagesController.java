package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.PrivilagesDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Privilages;
import com.rapidattendencesystem.project.service.PrivilagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/privilagectrl")
@CrossOrigin("*")
public class PrivilagesController {

    @Autowired
    private PrivilagesService privilagesService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addprivilages")
    public ResponseEntity<ResponseDTO> createPrivilages(@RequestBody List<PrivilagesDTO> privilagesDTOS){
        try {
            List<Privilages> H1 = privilagesService.createPrivilage(privilagesDTOS);
            if(!H1.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Privilages Added");
                responseDTO.setContent(H1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(privilagesDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deleteprivilage")
    public ResponseEntity<ResponseDTO> deletePrivilages(@RequestBody PrivilagesDTO privilagesDTO){
        try{
            Privilages p1 = privilagesService.deletePrivilage(privilagesDTO);
            if(p1.getId() > 0){
                responseDTO.setContent(p1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(privilagesDTO);
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

    @GetMapping("/getprivilages")
    public ResponseEntity<ResponseDTO> getPrivilages(){
        try{
            List<PrivilagesDTO> privilages = privilagesService.getPrivilages();
            if(!privilages.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(privilages);
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
}
