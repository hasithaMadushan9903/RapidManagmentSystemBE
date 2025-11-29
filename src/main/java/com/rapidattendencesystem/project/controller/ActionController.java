package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ActionDTO;
import com.rapidattendencesystem.project.dto.AppIconDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Action;
import com.rapidattendencesystem.project.entity.AppIcon;
import com.rapidattendencesystem.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/actionctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class ActionController {

    @Autowired
    private ActionService actionService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addaction")
    public ResponseEntity<ResponseDTO> addAction(@RequestBody ActionDTO actionDTO){
        try {
            Action A1 = actionService.createAction(actionDTO);
            if(A1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("App Icon Inserted");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(actionDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getactions")
    public ResponseEntity<ResponseDTO> getActions(){
        try{
            List<ActionDTO> actions = actionService.getActions();
            if(!actions.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(actions);
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
    public ResponseEntity<ResponseDTO> updateAction(@RequestBody ActionDTO actionDTO){
        try{
            Action a1 = actionService.updateAction(actionDTO);
            if(a1.getId() > 0){
                responseDTO.setContent(a1);
                responseDTO.setMassage("Deleted/Updated");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(actionDTO);
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
