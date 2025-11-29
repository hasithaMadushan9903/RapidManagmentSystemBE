package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ClassFeeDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.TeacherPaymentDTO;
import com.rapidattendencesystem.project.entity.ClassFee;
import com.rapidattendencesystem.project.service.TeacherPaymentService;
import com.rapidattendencesystem.project.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacherpaymentctrl")
@CrossOrigin("*")
public class TeacherPaymentController {

    @Autowired
    private TeacherPaymentService teacherPaymentService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getteacherpayment")
    public ResponseEntity<ResponseDTO> getTeacherPayment(){
        try{
            List<TeacherPaymentDTO> teacherPayments = teacherPaymentService.getTeacherPayment();
            if(!teacherPayments.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(teacherPayments);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Payments Empty");
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

    @GetMapping("/getteacherpaymentbyteacherid/{teacherId}")
    public ResponseEntity<ResponseDTO> getTeacherPaymentByTeacherId(@PathVariable int teacherId){
        try{
            List<TeacherPaymentDTO> teacherPayments = teacherPaymentService.getTeacherPaymentByTeacherId(teacherId);
            if(!teacherPayments.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(teacherPayments);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Payments Empty");
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

    @PostMapping("/addteacherpayment")
    public ResponseEntity<ResponseDTO> addTeacherPayment(@RequestBody TeacherPaymentDTO teacherPaymentDTO){
        try {
            TeacherPaymentDTO teacherPayment = teacherPaymentService.addTeacherPayment(teacherPaymentDTO);
            if(teacherPayment.getId()>0) {
                responseDTO.setCode("00");
                responseDTO.setContent(teacherPayment);
                responseDTO.setMassage("Records Inserted");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(teacherPaymentDTO);
                responseDTO.setMassage("Bad Request");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode("01");
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
