package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.EnrolmentCourse;
import com.rapidattendencesystem.project.service.EnrolmentCourseService;
import com.rapidattendencesystem.project.service.EnrolmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrolcoursectrl")
@CrossOrigin("*") //allow to access this controller from outside
public class EnrolmentCourseController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private EnrolmentCourseService enrolmentCourseService;

    @GetMapping("/getenrolcourse")
    public ResponseEntity<ResponseDTO> getEnrolments(){
        try {
            List<EnrolmentCourseDTO> res = enrolmentCourseService.getEnrolmentCourse();
            if(!res.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(res);
                responseDTO.setMassage("Success");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(null);
                responseDTO.setMassage("Bad Request");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode("02");
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deleteenrolmentcourse")
    public ResponseEntity<ResponseDTO> deleteEnrolmentCourse(@RequestBody EnrolmentCourseDTO enrolmentCourseDTO){
        try{
            EnrolmentCourse EC1 = enrolmentCourseService.deleteEnrolmentCourse(enrolmentCourseDTO);
            if(EC1.getId() > 0){
                responseDTO.setContent(EC1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(enrolmentCourseDTO);
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

    @PutMapping("/deleteenrolmentcourses")
    public ResponseEntity<ResponseDTO> deleteEnrolmentCourseS(@RequestBody List<EnrolmentCourseDTO> enrolmentCourseDTO){
        try{
            List<EnrolmentCourse> EC1 = enrolmentCourseService.deleteEnrolmentCourses(enrolmentCourseDTO);
            if(!EC1.isEmpty()){
                responseDTO.setContent(EC1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(enrolmentCourseDTO);
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

    @GetMapping("/existsbyisactiveandcourseid/{courseId}")
    public ResponseEntity<ResponseDTO> existsByIsActiveAndCourseId(@PathVariable int courseId){
        try {
            Boolean isExsist = enrolmentCourseService.existsByIsActiveAndCourseId(courseId);
            if(isExsist == true || isExsist == false) {
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(isExsist);
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

            }else {
                responseDTO.setCode("01");
                responseDTO.setMassage("Courses Are Empty");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getstudentcountbycourse")
    public ResponseEntity<ResponseDTO> getStudentCountByCourse(){
        try {
            List<CourseWiseStudentCountDTO> res = enrolmentCourseService.getStudentCountByCourse();
            if(!res.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(res);
                responseDTO.setMassage("Success");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(null);
                responseDTO.setMassage("Bad Request");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode("02");
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
