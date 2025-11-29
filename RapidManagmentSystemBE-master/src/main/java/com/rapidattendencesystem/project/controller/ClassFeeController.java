package com.rapidattendencesystem.project.controller;

import java.util.List;
import java.util.Optional;

import com.rapidattendencesystem.project.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rapidattendencesystem.project.entity.ClassFee;
import com.rapidattendencesystem.project.service.ClassFeeService;

@RestController
@RequestMapping("api/v1/classfeectrl")
@CrossOrigin("*")
public class ClassFeeController {

	@Autowired
	private ClassFeeService classFeeService;
	
	@Autowired
	private ResponseDTO responseDTO;

	@PostMapping("/getfirstpaiedmonth")
	public ResponseEntity<ResponseDTO> getFirstPaidClassFee(@RequestBody StudentWiseCourseDTO studentWiseCourseDTO){
		try {

			List<CourseWiseMonthDTO> courseWiseMonthDTOS = classFeeService.getFirstPaidClassFee(studentWiseCourseDTO);
			System.out.println("returned from service");
			if(!courseWiseMonthDTOS.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setContent(courseWiseMonthDTOS);
				responseDTO.setMassage("Success");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
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

	@PostMapping("/getunpaidmonths")
	public ResponseEntity<ResponseDTO> getUnpaidMonths(@RequestBody StudentWiseCourseDTO studentWiseCourseDTO){
		try {

			List<CourseWiseMonthsDTO> courseWiseMonthsDTOS = classFeeService.getUnpaidMonths(studentWiseCourseDTO);
			System.out.println("returned from service");
			if(!courseWiseMonthsDTOS.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setContent(courseWiseMonthsDTOS);
				responseDTO.setMassage("Success");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
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
	public ResponseEntity<ResponseDTO> findLastByStudentAndCourse(@RequestBody StudentCourseDTO studentCourseDTO){
		try {

			Optional<ClassFee> classFees = classFeeService.findLastByStudentAndCourse(studentCourseDTO);
			System.out.println("returned from service");
			if(!classFees.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setContent(classFees);
				responseDTO.setMassage("Records Inserted");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
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
	public ResponseEntity<ResponseDTO> findFirstByStudentAndCourse(@RequestBody StudentCourseDTO studentCourseDTO){
		try {

			Optional<ClassFee> classFees = classFeeService.findFirstByStudentAndCourse(studentCourseDTO);
			System.out.println("returned from service");
			if(!classFees.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setContent(classFees);
				responseDTO.setMassage("Records Inserted");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
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
	
	@PostMapping("/addclassfee")
	public ResponseEntity<ResponseDTO> addClassFee(@RequestBody ClassFeeDTO classFeeDTOs){
		try {
			System.out.println("enterd to ctrl");
			ClassFee classFees = classFeeService.addClassFee(classFeeDTOs);
			System.out.println("returned from service");
			if(classFees.getId()>0) {
				responseDTO.setCode("00");
				responseDTO.setContent(classFees);
				responseDTO.setMassage("Records Inserted");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(classFeeDTOs);
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

	@GetMapping("/getclassfee")
	public ResponseEntity<ResponseDTO> getClassFee(){
		try{
			List<ClassFeeDTO> classfees = classFeeService.getClassFee();
			if(!classfees.isEmpty()){
				responseDTO.setCode("00");
				responseDTO.setMassage("Succuss");
				responseDTO.setContent(classfees);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
			}else{
				responseDTO.setCode("01");
				responseDTO.setMassage("Grades Empty");
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
}
