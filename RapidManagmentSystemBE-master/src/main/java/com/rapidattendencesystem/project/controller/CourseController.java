package com.rapidattendencesystem.project.controller;

import java.util.List;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.CourseService;

@RestController
@RequestMapping("api/v1/coursectrl")
@CrossOrigin("*")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private ResponseDTO responseDTO;

	@PostMapping("/addcourse")
	public ResponseEntity<ResponseDTO> createCourse(@RequestBody CourseDTO courseDTO){
		try {
			Course C1 = courseService.createCourse(courseDTO);
			if(C1.getId()>0){
				responseDTO.setCode("00");
				responseDTO.setMassage("Course Inserted");
				responseDTO.setContent(C1);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
			}else{
				responseDTO.setCode("01");
				responseDTO.setMassage("Bad Request");
				responseDTO.setContent(courseDTO);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e){
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(null);
			return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/deletecourse")
	public ResponseEntity<ResponseDTO> deleteHall(@RequestBody CourseDTO courseDTO){
		try{
			Course c1 = courseService.deleteCourse(courseDTO);
			if(c1.getId() > 0){
				responseDTO.setContent(c1);
				responseDTO.setMassage("Deleted");
				responseDTO.setCode("00");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else{
				responseDTO.setContent(courseDTO);
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

	@PutMapping("/updatecourse")
	public ResponseEntity<ResponseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
		try{
			Course c1 = courseService.updateCourse(courseDTO);
			if(c1.getId() > 0){
				responseDTO.setContent(c1);
				responseDTO.setMassage("Updated");
				responseDTO.setCode("00");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else{
				responseDTO.setContent(courseDTO);
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

	@GetMapping("/getcoursesbyteacherid/{teacherId}")
	public ResponseEntity<ResponseDTO> getCoursesByTeacherId(@PathVariable int teacherId){
		try {
			List<CourseDTO> courses = courseService.getCoursesByTeacherId(teacherId);
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
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

	@GetMapping("/existsbyisactiveandgradeid/{gradeId}")
	public ResponseEntity<ResponseDTO> existsByIsActiveAndGradeId(@PathVariable int gradeId){
		try {
			Boolean isExsist = courseService.existsByIsActiveAndGradeId(gradeId);
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
	@GetMapping("/existsbyisactiveandteacherid/{teacherId}")
	public ResponseEntity<ResponseDTO> existsByIsActiveAndTeacherId(@PathVariable int teacherId){
		try {
			Boolean isExsist = courseService.existsByIsActiveAndTeacherId(teacherId);
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

	@GetMapping("/existsByIsActiveAndHallId/{hallId}")
	public ResponseEntity<ResponseDTO> existsByIsActiveAndHallId(@PathVariable int hallId){
		try {
			Boolean isExsist = courseService.existsByIsActiveAndHallId(hallId);
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

	@GetMapping("/existsByIsActiveAndSubjectId/{subjectId}")
	public ResponseEntity<ResponseDTO> existsByIsActiveAndSubjectId(@PathVariable int subjectId){
		try {
			Boolean isExsist = courseService.existsByIsActiveAndSubjectId(subjectId);
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

	@GetMapping("/getcanceledcoursesbydate/{day}/{date}")
	public ResponseEntity<ResponseDTO> getCanceledCoursesByDate(@PathVariable String day, @PathVariable String date){
		try {
			List<CourseDTO> courses = courseService.getCanceledCoursesByDate(day,date);
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
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

	@GetMapping("/getcoursesbydateandteacher/{date}/{teacherId}")
	public ResponseEntity<ResponseDTO> getCoursesByDateAndTeacher(@PathVariable String date,@PathVariable int teacherId){
		try {
			List<CourseDTO> courses = courseService.getCoursesByDateAndTeacher(date,teacherId);
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
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

	@GetMapping("/getcoursesbydateandstudentid/{date}/{studentId}")
	public ResponseEntity<ResponseDTO> getCoursesByStudentIdAndDate(@PathVariable String date,@PathVariable int studentId){
		try {
			List<CourseDTO> courses = courseService.getCoursesByStudentIdAndDate(studentId,date);
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
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

	@GetMapping("/getcoursesbyteacher/{teacherId}")
	public ResponseEntity<ResponseDTO> getCoursesByTeacher(@PathVariable int teacherId){
		try {
			List<CourseDTO> courses = courseService.getCoursesByTeacher(teacherId);
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
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

	@GetMapping("/getcoursesbydate/{date}")
	public ResponseEntity<ResponseDTO> getCoursesByDate(@PathVariable String date){
		try {
			List<CourseDTO> courses = courseService.getCoursesByDate(date);
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
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

	
	@GetMapping("/getcourses")
	public ResponseEntity<ResponseDTO> getCourses(){
		try {
			List<CourseDTO> courses = courseService.getCourses();
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
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
}
