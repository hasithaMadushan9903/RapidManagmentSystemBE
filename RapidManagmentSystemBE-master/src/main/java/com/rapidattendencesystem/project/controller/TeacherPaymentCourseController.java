package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.MonthWiseIncome;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.TeacherPaymentCourseService;
import com.rapidattendencesystem.project.service.TeacherPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacherpaymentcoursectrl")
@CrossOrigin("*")
public class TeacherPaymentCourseController {

    @Autowired
    private TeacherPaymentCourseService teacherPaymentCourseService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getteacherpaymentbymonth/{year}")
    public ResponseEntity<ResponseDTO> getTeacherPaymentByMonth(@PathVariable int year){
        try {

            List<MonthWiseIncome> teacherPaymentByMonth = teacherPaymentCourseService.getTeacherPaymentBymonth(year);
            System.out.println("returned from service");
            if(!teacherPaymentByMonth.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(teacherPaymentByMonth);
                responseDTO.setMassage("Success");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(null);
                responseDTO.setMassage("Bad Request");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode("01");
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
