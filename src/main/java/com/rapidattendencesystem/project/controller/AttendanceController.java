package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.Attendance;
import com.rapidattendencesystem.project.service.AttendanceService;
import com.rapidattendencesystem.project.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendancectrl")
@CrossOrigin("*") //allow to access this controller from outside
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/removeunattendmonths")
    public ResponseEntity<ResponseDTO> removeUnAttendMonths(@RequestBody CourseWiseMonthsWithStudentDTO courseWiseMonthsWithStudentDTO){
        try{
            CourseWiseMonthsWithStudentDTO courseWiseMonthsWithStudentDTO1 = attendanceService.removeUnAttendMonths(courseWiseMonthsWithStudentDTO);
            if(courseWiseMonthsWithStudentDTO1 != null){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(courseWiseMonthsWithStudentDTO1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallattendance")
    public ResponseEntity<ResponseDTO> getAllAtttendance(){
        try{
            List<AttendanceDTO> attendances = attendanceService.getAllAttendance();
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getattendancebycourseandyearandmonth")
    public ResponseEntity<ResponseDTO> getAttendanceByCourseAndYearAndMonth(@RequestBody AttendanceSearchDTO AttendanceSearchDTO){
        try{
            List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseAndYearAndMonth(AttendanceSearchDTO);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Attendance");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getcountbycourseandyearandmonthandstudent")
    public ResponseEntity<ResponseDTO> getCountByCourseAndYearAndMonthAndStudent(@RequestBody AttendanceSearchDTO AttendanceSearchDTO){
        try{
            int count = attendanceService.getCountByCourseAndYearAndMonthAndStudent(AttendanceSearchDTO);
            if(count != -1){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(count);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Attendance");
                responseDTO.setContent(-1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(-1);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getattendancebycourseanddate")
    public ResponseEntity<ResponseDTO> getAttendanceByCourseAndDate(@RequestBody AttendanceSearchDTO AttendanceSearchDTO){
        try{
            List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseAndDate(AttendanceSearchDTO);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Attendance");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateattendance")
    public ResponseEntity<ResponseDTO> updateAttendance(@RequestBody List<AttendanceDTO> attendanceDTOS){
        try{
            List<Attendance> attendances = attendanceService.updateAttendance(attendanceDTOS);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Attendance Updated");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(attendanceDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getattendancecountbymonthandcourse/{year}")
    public ResponseEntity<ResponseDTO> getAttendanceCountByMonthAndCourse(@PathVariable int year){
        try{
            List<AttendanceCountByMonthAndCourseDTO> attendancesCount = attendanceService.getAttendanceCountByMonthAndCourse(year);
            if(!attendancesCount.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendancesCount);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/markattendance")
    public ResponseEntity<ResponseDTO> markAttendance(@RequestBody List<AttendanceDTO> attendanceDTOS){
        try{
            List<Attendance> attendances = attendanceService.makeAttendance(attendanceDTOS);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Attendance Marked");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(attendanceDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
