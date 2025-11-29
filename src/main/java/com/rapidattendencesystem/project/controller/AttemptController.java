package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.Attempt;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.service.AttemptService;
import com.rapidattendencesystem.project.service.CourseService;
import com.rapidattendencesystem.project.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attemptctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getstudentquizreportdata/{studentId}")
    public ResponseEntity<ResponseDTO> getStudentQuizReportData(@PathVariable int studentId){
        try {
            List<StudentQuizReportDTO> sqr = attemptService.getStudentQuizReportData(studentId);
            if(!sqr.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(sqr);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @GetMapping("/getincomereportdataforfrontdeskofficer/{monthId}/{courseId}")
    public ResponseEntity<ResponseDTO> GetIncomeReportDataForFrontdeskOfficer(@PathVariable int monthId, @PathVariable int courseId){
        try {
            List<FrontDeskAttendanceReportDTO> rd = attemptService.GetIncomeReportDataForFrontdeskOfficer(monthId,courseId);
            if(!rd.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(rd);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @GetMapping("/getincomereportdata/{year}")
    public ResponseEntity<ResponseDTO> GetIncomeReportData(@PathVariable int year){
        try {
            List<IncomeReportDTO> sqr = attemptService.GetIncomeReportData(year);
            if(!sqr.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(sqr);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @GetMapping("/getexpencesreportdata/{year}")
    public ResponseEntity<ResponseDTO> GetExpencesReportData(@PathVariable int year){
        try {
            List<IncomeReportDTO> sqr = attemptService.GetExpencesReportData(year);
            if(!sqr.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(sqr);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @GetMapping("/getstudentattendancereportdata/{studentId}/{year}")
    public ResponseEntity<ResponseDTO> GetStudentAttendanceReportData(@PathVariable int studentId, @PathVariable int year){
        try {
            List<IncomeReportDTO> sqr = attemptService.GetStudentAttendanceReportData(studentId,year);
            if(!sqr.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(sqr);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @GetMapping("/getmanagerquizreportdata")
    public ResponseEntity<ResponseDTO> getManagerQuizReportData(){
        try {
            List<ManagerQuizReportDTO> mqr = attemptService.GetManagerQuizReportData();
            if(!mqr.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(mqr);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @GetMapping("/getteacherquizreportdata/{teacherId}")
    public ResponseEntity<ResponseDTO> GetTeacherQuizReportData(@PathVariable int teacherId){
        try {
            List<TeacherQuizReportDTO> tqr = attemptService.GetTeacherQuizReportData(teacherId);
            if(!tqr.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Report Data Found");
                responseDTO.setContent(tqr);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Data Found");
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

    @PostMapping("/addquizattempt")
    public ResponseEntity<ResponseDTO> addQuizAttempt(@RequestBody AttemptDTO attemptDTO){
        try {

            Attempt a1 = attemptService.addQuizAttempt(attemptDTO);
            ScoreDTO s1 = scoreService.addScore(a1);
            if(s1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Attempt Inserted");
                responseDTO.setContent(s1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(attemptDTO);
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
