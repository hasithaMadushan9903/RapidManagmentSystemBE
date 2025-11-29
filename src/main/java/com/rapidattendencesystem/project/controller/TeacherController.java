package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.CourseWisePaymentDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.SubjectDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.entity.Subject;
import com.rapidattendencesystem.project.entity.Teacher;
import com.rapidattendencesystem.project.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/teacherctrl")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ResponseDTO responseDTO;

    @PutMapping("/deleteteacher")
    public ResponseEntity<ResponseDTO> deleteTeacher(@RequestBody TeacherDTO teacherDTO){
        try{
            Teacher teacher = teacherService.updateTeacher(teacherDTO);
            if(teacher.getId() > 0){
                responseDTO.setContent(teacher);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
            }else{
                responseDTO.setContent(teacherDTO);
                responseDTO.setMassage("not Deleted");
                responseDTO.setCode("01");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");

            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateteacher")
    public ResponseEntity<ResponseDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO){
        try{
            Teacher teacher = teacherService.updateTeacher(teacherDTO);
            if(teacher.getId() > 0){
                responseDTO.setContent(teacher);
                responseDTO.setMassage("Updated");
                responseDTO.setCode("00");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
            }else{
                responseDTO.setContent(teacherDTO);
                responseDTO.setMassage("Not Updated");
                responseDTO.setCode("01");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");

            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createteacher")
    public ResponseEntity<ResponseDTO> addTeacher(@RequestBody TeacherDTO teacherDTO){
        try{
            Teacher teacher = teacherService.addTeacher(teacherDTO);
            if(teacher.getId() > 0){
                responseDTO.setContent(teacher);
                responseDTO.setMassage("Saved");
                responseDTO.setCode("00");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
            }else{
                responseDTO.setContent(teacherDTO);
                responseDTO.setMassage("not saved");
                responseDTO.setCode("01");

                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");

            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getteachers")
    public ResponseEntity<ResponseDTO> getTeachers(){
        try{
            List<TeacherDTO> teachers = teacherService.getTeachers();
            if(!teachers.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(teachers);

                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Not Found Any Records");
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

    @GetMapping("/getteacher/{tcode}")
    public ResponseEntity<ResponseDTO> getTeacherByUserCode(@PathVariable String tcode){
        try{
            Optional<Teacher> teacher = teacherService.getTeacherByUserCode(tcode);
            if(!(teacher.isEmpty())){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(teacher);

                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Not Found Any Records");
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

    @GetMapping("/getteacherearningsformonthbycoursewise/{teacherId}/{monthId}")
    public ResponseEntity<ResponseDTO> getTeacherEarningsForMonthByCourseWise(@PathVariable int teacherId, @PathVariable int monthId){
        try{
            List<CourseWisePaymentDTO> courseWisePaymentDTO = teacherService.getTeacherEarningsForMonthByCourseWise(teacherId,monthId);
            if(!courseWisePaymentDTO.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(courseWisePaymentDTO);

                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Not Found Any Records");
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

}
