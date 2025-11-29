package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.*;
import com.rapidattendencesystem.project.repo.AttendanceRepo;
import com.rapidattendencesystem.project.repo.MonthRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MonthRepo monthRepo;

    public CourseWiseMonthsWithStudentDTO removeUnAttendMonths(CourseWiseMonthsWithStudentDTO courseWiseMonthsWithStudentDTO){
        try{
            StudentDTO studentDTO = courseWiseMonthsWithStudentDTO.getStudent();
            List<CourseWiseMonthsDTO> payingCourseWiseMonths = courseWiseMonthsWithStudentDTO.getPayingCourseWiseMonths();
            int thisMonth = courseWiseMonthsWithStudentDTO.getThisMonthId();
            int thisYear = LocalDate.now().getYear();
            List<Month> allMonths = monthRepo.findAll();

            Student student = modelMapper.map(studentDTO,Student.class);

            for(CourseWiseMonthsDTO payingCourseWiseMonth : payingCourseWiseMonths){
                Course course = payingCourseWiseMonth.getCourse();
                List<Month> months = new ArrayList<>();

                for(Month month : allMonths){
                    if(!(attendanceRepo.existsByYearAndIsAttendAndMonth_IdAndStudent_IdAndCourse_Id(thisYear,true,month.getId(),studentDTO.getId(),course.getId()))){
                        months.add(month);
                    }
                }

                payingCourseWiseMonth.setMonths(months);
            }

            courseWiseMonthsWithStudentDTO.setPayingCourseWiseMonths(payingCourseWiseMonths);
            return courseWiseMonthsWithStudentDTO;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AttendanceDTO> getAllAttendance(){
        try{
            List<AttendanceDTO> attendances = modelMapper.map(attendanceRepo.findAll(), new TypeToken<List<AttendanceDTO>>() {}.getType()) ;
            return attendances;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AttendanceCountByMonthAndCourseDTO> getAttendanceCountByMonthAndCourse(int year){
        try{
            List<AttendanceCountByMonthAndCourseDTO> attendanceCountByMonthAndCourseDTO = new ArrayList<AttendanceCountByMonthAndCourseDTO>();
            List<Object[]> repoData = attendanceRepo.getAttendanceCountByCourseAndMonth(year);

            for(Object[] rd : repoData){
                AttendanceCountByMonthAndCourseDTO acmc = new AttendanceCountByMonthAndCourseDTO();

                int courseId = (int) rd[0];
                String courseName = (String) rd[1];
                int monthId = (int) rd[2];
                String monthName = (String) rd[3];
                int srudentcount = (int) rd[4];

                acmc.setCourseId(courseId);
                acmc.setCourseName(courseName);
                acmc.setMonthId(monthId);
                acmc.setMonthName(monthName);
                acmc.setStudentCount(srudentcount);

                attendanceCountByMonthAndCourseDTO.add(acmc);
            }
            return attendanceCountByMonthAndCourseDTO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AttendanceDTO> getAttendanceByCourseAndDate(AttendanceSearchDTO attendanceSearchDTO){
        try{
            Course c1 = modelMapper.map(attendanceSearchDTO.getCourse() , Course.class);
            Month m1 = modelMapper.map(attendanceSearchDTO.getMonth(), Month.class);
            int y1 = attendanceSearchDTO.getYear();
            int d1 = attendanceSearchDTO.getDate();
            List<AttendanceDTO> attendances = modelMapper.map(attendanceRepo.findByCourseAndYearAndMonthAndDate(c1,y1,m1,d1), new TypeToken<List<AttendanceDTO>>() {}.getType()) ;
            return attendances;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int getCountByCourseAndYearAndMonthAndStudent(AttendanceSearchDTO attendanceSearchDTO){
        try{
            Course c1 = modelMapper.map(attendanceSearchDTO.getCourse() , Course.class);
            Month m1 = modelMapper.map(attendanceSearchDTO.getMonth(), Month.class);
            int y1 = attendanceSearchDTO.getYear();
            int d1 = attendanceSearchDTO.getDate();
            Boolean ia = attendanceSearchDTO.getIsAttend();
            Student s1 = modelMapper.map(attendanceSearchDTO.getStudent(), Student.class);
            int count = attendanceRepo.countByCourseAndYearAndMonthAndStudentAndIsAttend(c1,y1,m1,s1,ia);
            return count;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public List<AttendanceDTO> getAttendanceByCourseAndYearAndMonth(AttendanceSearchDTO attendanceSearchDTO){
        try{
            Course c1 = modelMapper.map(attendanceSearchDTO.getCourse() , Course.class);
            Month m1 = modelMapper.map(attendanceSearchDTO.getMonth(), Month.class);
            int y1 = attendanceSearchDTO.getYear();
            List<AttendanceDTO> attendances = modelMapper.map(attendanceRepo.findByCourseAndYearAndMonth(c1,y1,m1), new TypeToken<List<AttendanceDTO>>() {}.getType()) ;
            return attendances;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Attendance> updateAttendance(List<AttendanceDTO> attendancesDTO){
        try{
            List<Attendance> attendances = modelMapper.map(attendancesDTO , new TypeToken<List<Attendance>>() {}.getType());
            List<Attendance> savedAttendance = attendanceRepo.saveAll(attendances);
            if(savedAttendance.isEmpty()){
                System.out.println("Empty Attendance");
                return attendances;
            }else{
                return savedAttendance;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Attendance> makeAttendance(List<AttendanceDTO> attendancesDTO){
        try{
            List<Attendance> attendances = modelMapper.map(attendancesDTO , new TypeToken<List<Attendance>>() {}.getType());
            List<Attendance> savedAttendance = attendanceRepo.saveAll(attendances);
            if(savedAttendance.isEmpty()){
                System.out.println("Empty Attendance");
                return attendances;
            }else{
                return savedAttendance;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
