package com.rapidattendencesystem.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidattendencesystem.project.dto.ParentDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.ParentsService;

@RestController
@RequestMapping("api/v1/parentctrl")
@CrossOrigin("*")
public class ParentController {

	@Autowired
	private ParentsService parentService;
	
	@Autowired
	private ResponseDTO responseDTO;
	
	@GetMapping("/getparents")
	public ResponseEntity<ResponseDTO> getParents(){
		try {
			List<ParentDTO> res = parentService.getallParent();
			if(!res.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setContent(res);
				responseDTO.setMassage("Success");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(null);
				responseDTO.setMassage("Parents Empty");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDTO.setCode("02");
			responseDTO.setContent(null);
			responseDTO.setMassage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getparentbyid/{nic}")
	public ResponseEntity<ResponseDTO> getParentsById(@PathVariable String nic){
		try {
			ParentDTO res = parentService.getParentById(nic);
			System.out.println("RES SET");
			if(res != null) {
				responseDTO.setCode("00");
				responseDTO.setContent(res);
				responseDTO.setMassage("Success");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(null);
				responseDTO.setMassage("NIC not exsist");
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
