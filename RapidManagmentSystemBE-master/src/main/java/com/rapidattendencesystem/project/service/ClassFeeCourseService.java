package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.CourseWiseClassFeeDTO;
import com.rapidattendencesystem.project.dto.MonthCourseDTO;
import com.rapidattendencesystem.project.dto.MonthWiseIncome;
import com.rapidattendencesystem.project.dto.StudentCourseDTO;
import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Month;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.repo.ClassFeeCourseRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClassFeeCourseService {

    @Autowired
    private ClassFeeCourseRepo classFeeCourseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<Student> getClassFeeCourseByMonthAndCourse(MonthCourseDTO monthCourseDTO){
        try{
            int thisYear = LocalDate.now().getYear();
            LocalDateTime start = LocalDateTime.of(thisYear, 1, 1, 0, 0);
            LocalDateTime end = start.plusYears(1);
            Month m1 = modelMapper.map(monthCourseDTO.getMonth(),Month.class);
            Course c1 = modelMapper.map(monthCourseDTO.getCourse(),Course.class);
            return classFeeCourseRepo.findClassFeeCourse_ClassFee_StudentByCourseAndMonth(c1,m1,start,end);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<MonthWiseIncome> getStudentClassFeeByCourse(int year){
        try{
            List<Object[]> counts = classFeeCourseRepo.getStudentClassFeeByCourse(year);
            List<MonthWiseIncome> monthWiseIncomes = new ArrayList<>();
            BigDecimal zero = new BigDecimal("0.00");

            for (Object[] count : counts){
                MonthWiseIncome dto = new MonthWiseIncome();
                if (count[0] != null) {
                    dto.setMonthId((int) count[0]);
                } else {
                    // Set a default value or handle the null case
                    dto.setMonthId(0);  // Default value for null monthId
                }
                if (count[1] != null) {
                    dto.setAmmount((BigDecimal) count[1]);
                } else {
                    // Set a default value or handle the null case
                    dto.setAmmount((BigDecimal) zero);  // Default value for null monthId
                }

                monthWiseIncomes.add(dto);
            }

            return monthWiseIncomes;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseWiseClassFeeDTO> findLastByStudentAndCourse(List<StudentCourseDTO> studentCourseDTO){
        try{
            List<CourseWiseClassFeeDTO> courseWiseClassFeeDTOs = new ArrayList<>();
            for (StudentCourseDTO studentCourseDTO1 : studentCourseDTO){
                Course c1 = modelMapper.map(studentCourseDTO1.getCourse() , Course.class);
                Student s1 = modelMapper.map(studentCourseDTO1.getStudent() , Student.class);

                ClassFeeCourse cc1 =  classFeeCourseRepo.findTop1ByCourseAndClassFee_StudentOrderByMonthDesc(c1,s1);
                CourseWiseClassFeeDTO courseWiseClassFeeDTO = new CourseWiseClassFeeDTO();
                courseWiseClassFeeDTO.setCourse(c1);
                courseWiseClassFeeDTO.setClassFeeCourse(cc1);
                courseWiseClassFeeDTOs.add(courseWiseClassFeeDTO);
            }
            if (!courseWiseClassFeeDTOs.isEmpty()){
                return courseWiseClassFeeDTOs;
            }else{
                return null;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseWiseClassFeeDTO> findFirstByStudentAndCourse(List<StudentCourseDTO> studentCourseDTO){
        try{
            List<CourseWiseClassFeeDTO> courseWiseClassFeeDTOs = new ArrayList<>();
            for (StudentCourseDTO studentCourseDTO1 : studentCourseDTO){
                Course c1 = modelMapper.map(studentCourseDTO1.getCourse() , Course.class);
                Student s1 = modelMapper.map(studentCourseDTO1.getStudent() , Student.class);

                ClassFeeCourse cc1 =  classFeeCourseRepo.findTop1ByCourseAndClassFee_StudentAndClassFee_IsActiveOrderByMonthAsc(c1,s1,true);
                CourseWiseClassFeeDTO courseWiseClassFeeDTO = new CourseWiseClassFeeDTO();
                courseWiseClassFeeDTO.setCourse(c1);
                courseWiseClassFeeDTO.setClassFeeCourse(cc1);
                courseWiseClassFeeDTOs.add(courseWiseClassFeeDTO);
            }

            if (!courseWiseClassFeeDTOs.isEmpty()){
                return courseWiseClassFeeDTOs;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
