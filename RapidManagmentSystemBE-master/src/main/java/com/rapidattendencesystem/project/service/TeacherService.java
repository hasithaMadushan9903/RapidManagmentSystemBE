package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.CourseWisePaymentDTO;
import com.rapidattendencesystem.project.dto.SubjectDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Subject;
import com.rapidattendencesystem.project.entity.Teacher;
import com.rapidattendencesystem.project.repo.TeacherRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Teacher updateTeacher(TeacherDTO teacherDTO){
        try {
            Teacher teacher = modelMapper.map(teacherDTO , Teacher.class);
            if(teacherRepo.existsById(teacher.getId())){
                Teacher teacher2 = teacherRepo.save(teacher);
                System.out.println("Successfully Updated");
                return teacher2;
            }else{
                System.out.println("User Not Exist");
                return teacher;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Teacher addTeacher(TeacherDTO teacherDTO){
        try {
            Teacher teacher = modelMapper.map(teacherDTO , Teacher.class);
            if(teacherRepo.existsById(teacher.getId())){
                System.out.println("Already Added");
                return teacher;
            }else{
                Teacher teacher1 = teacherRepo.save(teacher);
                System.out.println("Successfully Saved");
                return teacher1;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<TeacherDTO> getTeachers(){
        try {
            return modelMapper.map(teacherRepo.findByIsActive(true) , new TypeToken<List<TeacherDTO>>() {}.getType());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Optional<Teacher> getTeacherByUserCode(String tCode){
        try{
            Optional<Teacher> t1 = teacherRepo.findTeacherByTcode(tCode);

            return t1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseWisePaymentDTO> getTeacherEarningsForMonthByCourseWise(int teacherId, int monthId){
        try{
            int thisYear = LocalDate.now().getYear();
            List<Object[]> results = teacherRepo.getTeacherEarningsForMonthByCourseWise(teacherId,monthId,thisYear);
            List<CourseWisePaymentDTO> courseWisePaymentDTO = new ArrayList<>();
            for (Object[] result : results){
                int courseId = (int) result[0];
                int studentCount = (int) result[1];
                Long amount = (Long) result[2];
                Boolean isPayed = (Boolean) result[3];

                CourseWisePaymentDTO dto = new CourseWisePaymentDTO();
                dto.setCourseId(courseId);
                dto.setAmount(amount);
                dto.setStudentCount(studentCount);
                dto.setIsPayed(isPayed);

                courseWisePaymentDTO.add(dto);
            }
            return courseWisePaymentDTO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
