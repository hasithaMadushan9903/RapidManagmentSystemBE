package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.service.ClassFeeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/classfeecoursectrl")
@CrossOrigin("*")
public class ClassFeeCourseController {

    @Autowired
    private ClassFeeCourseService classFeeCourseService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getstudentclassfeegroupbycourse/{year}")
    public ResponseEntity<ResponseDTO> getStudentClassFeeByCourse(@PathVariable int year){
        try {

            List<MonthWiseIncome> studentCountByCourseDTO = classFeeCourseService.getStudentClassFeeByCourse(year);
            System.out.println("returned from service");
            if(!studentCountByCourseDTO.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(studentCountByCourseDTO);
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

    @PostMapping("/findlastbystudentandcourse")
    public ResponseEntity<ResponseDTO> findLastByStudentAndCourse(@RequestBody List<StudentCourseDTO> studentCourseDTO){
        try {

            List<CourseWiseClassFeeDTO> classFees = classFeeCourseService.findLastByStudentAndCourse(studentCourseDTO);
            System.out.println("returned from service");
            if(!classFees.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(classFees);
                responseDTO.setMassage("Records Inserted");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(null);
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

    @PostMapping("/findfirstbystudentandcourse")
    public ResponseEntity<ResponseDTO> findFirstByStudentAndCourse(@RequestBody List<StudentCourseDTO> studentCourseDTO){
        try {

            List<CourseWiseClassFeeDTO> classFees = classFeeCourseService.findFirstByStudentAndCourse(studentCourseDTO);
            System.out.println("returned from service");
            if(!classFees.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(classFees);
                responseDTO.setMassage("Records Inserted");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(null);
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

    @PostMapping("/getclassfeecoursebymonthandcourse")
    public ResponseEntity<ResponseDTO> getClassFeeCourseByMonthAndCourse(@RequestBody MonthCourseDTO monthCourseDTO){
        try {
            List<Student> classFees = classFeeCourseService.getClassFeeCourseByMonthAndCourse(monthCourseDTO);
            System.out.println("returned from service");
            if(!classFees.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(classFees);
                responseDTO.setMassage("Records Have");
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
