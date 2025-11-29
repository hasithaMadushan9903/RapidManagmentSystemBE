package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ClassFeeCourseDTO;
import com.rapidattendencesystem.project.dto.ClassFeeDTO;
import com.rapidattendencesystem.project.dto.TeacherPaymentDTO;
import com.rapidattendencesystem.project.entity.ClassFee;
import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.TeacherPayment;
import com.rapidattendencesystem.project.entity.TeacherPaymentCourse;
import com.rapidattendencesystem.project.repo.TeacherPaymentRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherPaymentService {

    @Autowired
    private TeacherPaymentRepo teacherPaymentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<TeacherPaymentDTO> getTeacherPayment(){
        try{
            return modelMapper.map(teacherPaymentRepo.findAll(), new TypeToken<List<TeacherPaymentDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<TeacherPaymentDTO> getTeacherPaymentByTeacherId(int teacherId){
        try{
            return modelMapper.map(teacherPaymentRepo.findByTeacher_IdOrderByIdDesc(teacherId), new TypeToken<List<TeacherPaymentDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public TeacherPaymentDTO addTeacherPayment(TeacherPaymentDTO teacherPaymentDTO) {
        try{
            if(teacherPaymentRepo.existsById(teacherPaymentDTO.getId())) {
                return null;
            }else {
                TeacherPayment teacherPayment = modelMapper.map(teacherPaymentDTO, TeacherPayment.class);
                List<TeacherPaymentCourse> teacherPaymentCourses = new ArrayList<>();
                for(TeacherPaymentCourse teacherPaymentCourse : teacherPaymentDTO.getTeacherPaymentCourse()) {
                    TeacherPaymentCourse teacherPaymentCourse1 = modelMapper.map(teacherPaymentCourse, TeacherPaymentCourse.class);
                    teacherPaymentCourse.setTeacherPayment(teacherPayment);
                    teacherPaymentCourses.add(teacherPaymentCourse);
                }
                teacherPayment.setTeacherPaymentCourse(teacherPaymentCourses);
                teacherPayment.setDate(LocalDateTime.now());
                return modelMapper.map(teacherPaymentRepo.save(teacherPayment) , TeacherPaymentDTO.class);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
