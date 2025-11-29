package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.AppIconDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.RoleDTO;
import com.rapidattendencesystem.project.entity.AppIcon;
import com.rapidattendencesystem.project.entity.Role;
import com.rapidattendencesystem.project.service.AppIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appiconctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class AppIconController {

    @Autowired
    private AppIconService appIconService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addappicon")
    public ResponseEntity<ResponseDTO> addAppIcon(@RequestBody AppIconDTO appIconDTO){
        try {
            AppIcon A1 = appIconService.createAppIcon(appIconDTO);
            if(A1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("App Icon Inserted");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(appIconDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getappicons")
    public ResponseEntity<ResponseDTO> getAppIcons(){
        try{
            List<AppIconDTO> appicons = appIconService.getAppIcon();
            if(!appicons.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(appicons);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Role Empty");
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

    @PutMapping("/updateappicon")
    public ResponseEntity<ResponseDTO> updateAppIco(@RequestBody AppIconDTO appIconDTO){
        try{
            AppIcon a1 = appIconService.updateAppIcon(appIconDTO);
            if(a1.getId() > 0){
                responseDTO.setContent(a1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(appIconDTO);
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

}
