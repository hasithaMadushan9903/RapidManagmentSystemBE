package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.RoleDTO;
import com.rapidattendencesystem.project.entity.Grade;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Role;
import com.rapidattendencesystem.project.service.HallService;
import com.rapidattendencesystem.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rolectrl")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addrole")
    public ResponseEntity<ResponseDTO> addRole(@RequestBody RoleDTO roleDTO){
        try {
            Role R1 = roleService.createRole(roleDTO);
            if(R1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Role Inserted");
                responseDTO.setContent(R1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(roleDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getroles")
    public ResponseEntity<ResponseDTO> getRoles(){
        try{
            List<RoleDTO> roles = roleService.getRoles();
            if(!roles.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(roles);
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

    @PutMapping("/updaterole")
    public ResponseEntity<ResponseDTO> updaterole(@RequestBody RoleDTO roleDTO){
        try{
            Role r1 = roleService.updateRole(roleDTO);
            if(r1.getId() > 0){
                responseDTO.setContent(r1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(roleDTO);
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
