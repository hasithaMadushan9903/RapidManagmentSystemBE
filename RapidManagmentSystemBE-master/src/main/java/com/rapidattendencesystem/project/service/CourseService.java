package com.rapidattendencesystem.project.service;

import java.util.List;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Hall;
import com.sun.jdi.event.StepEvent;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.repo.CourseRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private ModelMapper modalMapper;
	
	public List<CourseDTO> getCourses() {
		return modalMapper.map(courseRepo.findByIsActive(true), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public List<CourseDTO> getCoursesByDate(String date) {
		return modalMapper.map(courseRepo.findByDateAndIsActive(date,true), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public List<CourseDTO> getCoursesByDateAndTeacher(String date, int teacherId) {
		return modalMapper.map(courseRepo.findByDateAndIsActiveAndTeacherCode(true, date,teacherId ), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public List<CourseDTO> getCoursesByTeacher(int teacherId) {
		return modalMapper.map(courseRepo.findByIsActiveAndTeacher_Id(true,teacherId ), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public List<CourseDTO> getCanceledCoursesByDate(String day, String date) {
		return modalMapper.map(courseRepo.findCanceledCoursesByDate(true,day,date), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public List<CourseDTO> getCoursesByStudentIdAndDate(int studentId, String date) {
		return modalMapper.map(courseRepo.findCoursesByStudentIdAndDate(studentId,date,true), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public List<CourseDTO> getCoursesByTeacherId(int teacherId) {
		return modalMapper.map(courseRepo.findAllByTeacherId(teacherId), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public Boolean existsByIsActiveAndGradeId(int gradeId) {
		return courseRepo.existsByIsActiveAndGrade_Id(true,gradeId);
	}

	public Boolean existsByIsActiveAndTeacherId(int teacherId) {
		return courseRepo.existsByIsActiveAndTeacher_Id(true,teacherId);
	}

	public Boolean existsByIsActiveAndSubjectId(int subjectId) {
		return courseRepo.existsByIsActiveAndSubject_Id(true,subjectId);
	}

	public Boolean existsByIsActiveAndHallId(int hallId) {
		return courseRepo.existsByIsActiveAndHall_Id(true,hallId);
	}

	public Course deleteCourse(CourseDTO courseDTO){
		try{
			Course C1 = modalMapper.map(courseDTO , Course.class);
			return courseRepo.save(C1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Course updateCourse(CourseDTO courseDTO){
		try{
			Course C1 = modalMapper.map(courseDTO , Course.class);
			return courseRepo.save(C1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Course createCourse(CourseDTO courseDTO){
		try {
			Course C1 = modalMapper.map(courseDTO, Course.class);
			if(courseRepo.existsById(C1.getId())){
				System.out.println("user exist");
				return C1;
			}else{
				Course C2 = courseRepo.save(C1);
				System.out.println(C2);
				return C2;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
            return modalMapper.map(courseDTO, Course.class);
		}
	}

}
