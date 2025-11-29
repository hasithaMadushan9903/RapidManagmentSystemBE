package com.rapidattendencesystem.project.service;

import java.util.List;
import java.util.Optional;

import com.rapidattendencesystem.project.dto.ParentDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidattendencesystem.project.dto.StudentDTO;
import com.rapidattendencesystem.project.entity.Parent;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.repo.ParentRepo;
import com.rapidattendencesystem.project.repo.StudentRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional 
public class StudentsService {
	@Autowired
	private StudentRepo studentrepo;
	
	@Autowired
	private ParentRepo parentRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	public Student deleteStudent(StudentDTO student){
		try {
			Student S1 = modelmapper.map(student, Student.class);
			return studentrepo.save(S1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Student updateStudent(StudentDTO student){
		try {
			Student S1 = modelmapper.map(student, Student.class);
			return studentrepo.save(S1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int removeStudent(StudentDTO student){
		try {
			Student S1 =  modelmapper.map(student, Student.class);
			if(studentrepo.existsById(S1.getId())){
				Student S2 = studentrepo.save(S1);
				return 1;
			}else{
				return 0;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	public Student addStudent(StudentDTO student) {
		try {
			Student S1 =  modelmapper.map(student, Student.class);
			System.out.println("s1");
			System.out.println(S1); 
			System.out.println();
			System.out.println(studentrepo.existsById(S1.getId()));
			if(studentrepo.existsById(S1.getId())) {
				System.out.println("a");
				System.out.println("user exist");
				return S1;
			}else {
				System.out.println("d");
				Parent p1 = parentRepo.save(S1.getParent());
				S1.setParent(p1);
				Student S2 = studentrepo.save(S1);
				System.out.println(S2);
				System.out.println("user saved succssfully    " + S2.getParent().getId());
				return S2;
				
			}
			
		} catch (Exception e) {
			System.out.println("c");
			System.out.println(e.getMessage());
			return modelmapper.map(student, Student.class);
		}
	}
	

	public List<StudentDTO> getStudents() {
		return modelmapper.map(studentrepo.findByIsActive(true), new TypeToken<List<StudentDTO>>() {}.getType()) ;
	}

	public List<StudentDTO> getStudentsByTeacherId(int teacherId) {
		return modelmapper.map(studentrepo.findAllByTeacherId(teacherId,true), new TypeToken<List<StudentDTO>>() {}.getType()) ;
	}

	public StudentDTO getStudentByScode(String scode){
		try{
			String s = scode.substring(6);
			int sid = Integer.parseInt(s);
			Student std = studentrepo.findStudentByScodeAndIsActive(scode,true);
			if(std.getId()>0) {
				StudentDTO S1 = modelmapper.map(std, StudentDTO.class);
				return S1;
			}else{
				return null;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
