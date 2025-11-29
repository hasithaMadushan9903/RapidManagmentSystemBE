package com.rapidattendencesystem.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rapidattendencesystem.project.dto.EnrolmentCourseDTO;
import com.rapidattendencesystem.project.dto.EnrolmentDTO;
import com.rapidattendencesystem.project.entity.Enrolment;
import com.rapidattendencesystem.project.entity.EnrolmentCourse;
import com.rapidattendencesystem.project.repo.EnrolmentRepo;

@Service
@Transactional
public class EnrolmentServices {

	@Autowired
	private EnrolmentRepo enrolmentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<EnrolmentDTO> getEnrolment() {
		return modelMapper.map(enrolmentRepo.findAll(), new TypeToken<List<EnrolmentDTO>>() {}.getType()) ;
	}
	
	public Enrolment addEnrolment(EnrolmentDTO enrolment) {

		try{
			Boolean isAnyCourseAlreadyExsist = false;
			Enrolment e1 = modelMapper.map(enrolment, Enrolment.class);

			List<EnrolmentCourse> enrolmentCoursesList = new ArrayList<>();
			enrolmentCoursesList = e1.getEnrolmentCourses();

			for(EnrolmentCourse enrolmentCourse : enrolmentCoursesList){
				List<Enrolment> e2 = enrolmentRepo.findEnrolmentByStudentIdAndCourseId(e1.getStudent().getId(),enrolmentCourse.getCourse().getId(),true);

				if(!(e2.isEmpty())){
					isAnyCourseAlreadyExsist = true;
					break;
				}
			}

			if(isAnyCourseAlreadyExsist) {
				return null;
			}else {
				Enrolment enrlmnt = modelMapper.map(enrolment, Enrolment.class);
				List<EnrolmentCourse> enrolmentCourses = new ArrayList<>();
				for(EnrolmentCourseDTO enrolmentCourseDTO : enrolment.getEnrolmentCourses()) {
					EnrolmentCourse enrlmntCorse = modelMapper.map(enrolmentCourseDTO, EnrolmentCourse.class);
					enrlmntCorse.setEnrolment(enrlmnt);
					enrolmentCourses.add(enrlmntCorse);
				}
				enrlmnt.setEnrolmentCourses(enrolmentCourses);
				enrlmnt.setDate(LocalDateTime.now());
				return enrolmentRepo.save(enrlmnt);
			}


		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
    }
	
	public List<Enrolment> setEnrolment(List<EnrolmentDTO> enrolmentDTO){
		try {
			List<Enrolment> enrolments = new ArrayList<Enrolment>();
			for (EnrolmentDTO enrolment : enrolmentDTO) {
				enrolments.add(modelMapper.map(enrolment, Enrolment.class));
			}
			List<Enrolment> e = enrolmentRepo.saveAll(enrolments);
			return e;
		} catch (Exception e) {
			List<Enrolment> enrolments = new ArrayList<Enrolment>();
			for (EnrolmentDTO enrolment : enrolmentDTO) {
				enrolments.add(modelMapper.map(enrolment, Enrolment.class));
			}
			return enrolments;
		}
		
	}
}