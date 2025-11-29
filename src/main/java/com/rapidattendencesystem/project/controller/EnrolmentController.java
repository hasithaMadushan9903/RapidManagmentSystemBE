package com.rapidattendencesystem.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidattendencesystem.project.dto.EnrolmentDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Enrolment;
import com.rapidattendencesystem.project.service.EnrolmentServices;

@RestController
@RequestMapping("api/v1/enrolmentctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class EnrolmentController {
	
	@Autowired
	private ResponseDTO responseDTO;
	
	@Autowired
	private EnrolmentServices enrolmentService;
	
	@PostMapping("/addEnrolment")
	public ResponseEntity<ResponseDTO> addEnrolment(@RequestBody EnrolmentDTO enrolment){
		System.out.println("enterd into ctrl");
		try {
			Enrolment enrol = enrolmentService.addEnrolment(enrolment);
			if(enrol != null) {
				responseDTO.setCode("00");
				responseDTO.setContent(enrol);
				responseDTO.setMassage("Record Inserted");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(null);
				responseDTO.setMassage("Error Occered");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}
		} catch (Exception e) {
				responseDTO.setCode("01");
				responseDTO.setContent(null);
				responseDTO.setMassage(e.getMessage());
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addEnrolments")
	public ResponseEntity<ResponseDTO> addEnrolments(@RequestBody List<EnrolmentDTO> enrolments){
		try {
			List<Enrolment> enrols = enrolmentService.setEnrolment(enrolments);
			if(enrols.get(0).getId() > 0) {
				responseDTO.setCode("00");
				responseDTO.setContent(enrols);
				responseDTO.setMassage("Records Inserted");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(enrols);
				responseDTO.setMassage("Bad Request");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDTO.setCode("02");
			responseDTO.setContent(null);
			responseDTO.setMassage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getenrolments")
	public ResponseEntity<ResponseDTO> getEnrolments(){
		try {
			List<EnrolmentDTO> res = enrolmentService.getEnrolment();
			if(!res.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setContent(res);
				responseDTO.setMassage("Success");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(null);
				responseDTO.setMassage("Bad Request");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDTO.setCode("02");
			responseDTO.setContent(null);
			responseDTO.setMassage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
