package com.rapidattendencesystem.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;

import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.StudentDTO;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.service.StudentsService;

@RestController
@RequestMapping("api/v1/studentctrl")
@CrossOrigin("*") //allow to access this controller from outside
public class StudentContoller {
	
//	@GetMapping("/students")
//	public ModelAndView studentUI() {
//		ModelAndView modelAndVeiwObj = new ModelAndView();
//		modelAndVeiwObj.setViewName("modelAndVeiwObj");
//		return modelAndVeiwObj
//	}
	@Autowired
	private StudentsService studentService;
	
	@Autowired
	private ResponseDTO responseDTO;

	@PutMapping("/removestudent")
	public ResponseEntity<ResponseDTO> removeStudent(@RequestBody StudentDTO studentDTO){
		try {
			int isRemoved = studentService.removeStudent(studentDTO);
			if(isRemoved == 1){
				responseDTO.setCode("00");
				responseDTO.setMassage("Record Removed");
				responseDTO.setContent(true);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
			}else{
				responseDTO.setCode("01");
				responseDTO.setMassage("Record Not Exsist");
				responseDTO.setContent(false);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.NOT_FOUND);
			}
		}catch (Exception e){
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(false);
			return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updatestudent")
	public ResponseEntity<ResponseDTO> updateStudent(@RequestBody StudentDTO studentDTO){
		try {
			Student S1 = studentService.updateStudent(studentDTO);
			if(S1.getId() > 0){
				responseDTO.setCode("00");
				responseDTO.setContent(S1);
				responseDTO.setMassage("Record Updated");
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
			}else{
				responseDTO.setCode("01");
				responseDTO.setContent(null);
				responseDTO.setMassage("Error Occered");
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
			}
		}catch (Exception e){
			responseDTO.setCode("02");
			responseDTO.setContent(null);
			responseDTO.setMassage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/addstudent")
	public ResponseEntity<ResponseDTO> addStudents(@RequestBody StudentDTO studentDTO){
		try {
			System.out.println("studentdto");
			System.out.println(studentDTO);
			Student res = studentService.addStudent(studentDTO);
			System.out.println("res");
			System.out.println(res);
			if(res.getId() > 0) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Record Inserted");
				responseDTO.setContent(res);
				System.out.println("responseDTO.getContent()");
				System.out.println(responseDTO.getContent());
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Error Occered");
				responseDTO.setContent(studentDTO);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(null);
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getstudents")
	public ResponseEntity<ResponseDTO> getStudents(){
		try {
			List<StudentDTO> students= studentService.getStudents();
			if(!students.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(students);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Sudents are empty");
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

	@GetMapping("/getstudentsbyteacherid/{teacherId}")
	public ResponseEntity<ResponseDTO> getStudentsByTeacherId(@PathVariable int teacherId){
		try {
			List<StudentDTO> students= studentService.getStudentsByTeacherId(teacherId);
			if(!students.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(students);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Sudents are empty");
				responseDTO.setContent(null);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}
		} catch (Exception e) {
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(null);
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getstudentbyscode/{scode}")
	public ResponseEntity<ResponseDTO> getStudentByScode(@PathVariable String scode){
		try {
			StudentDTO S1 = studentService.getStudentByScode(scode);
			if(S1 == null) {
				responseDTO.setCode("01");
				responseDTO.setMassage("Sudent is empty");
				responseDTO.setContent(null);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(S1);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(null);
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
