package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.*;
import com.rapidattendencesystem.project.repo.AnswersRepo;
import com.rapidattendencesystem.project.repo.AttemptRepo;
import com.rapidattendencesystem.project.repo.AttendanceRepo;
import com.rapidattendencesystem.project.repo.ScoreRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttemptService {

    @Autowired
    private AttemptRepo attemptRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<StudentQuizReportDTO> getStudentQuizReportData(int studentId){
        try{
            List<Object[]> results = attemptRepo.GetStudentQuizReportDetails(studentId);
            List<StudentQuizReportDTO> studentQuizReportDTOS = new ArrayList<>();

            for(Object[] obj : results){
                String quizName = (String) obj[0];
                String courseCode = (String) obj[1];
                String courseName = (String) obj[2];
                String attemptDate = (String) obj[3];
                int marks = (int) obj[4];
                int noOfQuiz = (int) obj[5];

                StudentQuizReportDTO studentQuizReportDTO = new StudentQuizReportDTO();
                studentQuizReportDTO.setQuizName(quizName);
                studentQuizReportDTO.setCourseCode(courseCode);
                studentQuizReportDTO.setCourseName(courseName);
                studentQuizReportDTO.setAttemptDate(attemptDate);
                studentQuizReportDTO.setMarks(marks);
                studentQuizReportDTO.setNoOfQuestionInQuiz(noOfQuiz);

                studentQuizReportDTOS.add(studentQuizReportDTO);
            }

            return studentQuizReportDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<FrontDeskAttendanceReportDTO> GetIncomeReportDataForFrontdeskOfficer(int monthId, int courseId){
        try{
            List<Object[]> results = attemptRepo.GetIncomeReportDataForFrontdeskOfficer(monthId,courseId);
            List<FrontDeskAttendanceReportDTO> frontDeskAttendanceReportDTOS = new ArrayList<>();

            for(Object[] obj : results){
                String studentName = (String) obj[0];
                String studentCode = (String) obj[1];
                String courseCode = (String) obj[2];
                String courseName = (String) obj[3];
                String reciptNumber = (String) obj[4];
                BigDecimal paidAmounnt = (BigDecimal) obj[5];
                String payementCategory = (String) obj[6];
                String payedDate = (String) obj[7];

                FrontDeskAttendanceReportDTO frontDeskAttendanceReportDTO = new FrontDeskAttendanceReportDTO();
                frontDeskAttendanceReportDTO.setStudnetName(studentName);
                frontDeskAttendanceReportDTO.setCourseCode(courseCode);
                frontDeskAttendanceReportDTO.setCourseName(courseName);
                frontDeskAttendanceReportDTO.setStudentCode(studentCode);
                frontDeskAttendanceReportDTO.setReciptNumber(reciptNumber);
                frontDeskAttendanceReportDTO.setPaidAmount(paidAmounnt);
                frontDeskAttendanceReportDTO.setPayementCategory(payementCategory);
                frontDeskAttendanceReportDTO.setPayedDate(payedDate);

                frontDeskAttendanceReportDTOS.add(frontDeskAttendanceReportDTO);
            }

            return frontDeskAttendanceReportDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<IncomeReportDTO> GetExpencesReportData(int year){
        try{
            List<Object[]> results = attemptRepo.GetExpencesReportData(year);
            List<IncomeReportDTO> incomeReportDTOS = new ArrayList<>();
            for(Object[] obj : results){
                String teacherName = (String) obj[0];
                int janAmount = (int) obj[1];
                int febAmount = (int) obj[2];
                int marAmount = (int) obj[3];
                int aprAmount = (int) obj[4];
                int mayAmount = (int) obj[5];
                int junAmount = (int) obj[6];
                int julAmount = (int) obj[7];
                int augAmount = (int) obj[8];
                int sepAmount = (int) obj[9];
                int octAmount = (int) obj[10];
                int novAmount = (int) obj[11];
                int decAmount = (int) obj[12];
                int total = (int) obj[13];

                IncomeReportDTO incomeReportDTO = new IncomeReportDTO();

                incomeReportDTO.setDescriptiopn(teacherName);
                incomeReportDTO.setJanAmount(janAmount);
                incomeReportDTO.setFebAmount(febAmount);
                incomeReportDTO.setMarAmount(marAmount);
                incomeReportDTO.setAprAmount(aprAmount);
                incomeReportDTO.setMayAmount(mayAmount);
                incomeReportDTO.setJunAmount(junAmount);
                incomeReportDTO.setJulAmount(julAmount);
                incomeReportDTO.setAugAmount(augAmount);
                incomeReportDTO.setSepAmount(sepAmount);
                incomeReportDTO.setSepAmount(octAmount);
                incomeReportDTO.setNovAmount(novAmount);
                incomeReportDTO.setDecAmount(decAmount);
                incomeReportDTO.setTotal(total);

                incomeReportDTOS.add(incomeReportDTO);
            }

            return incomeReportDTOS;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<IncomeReportDTO> GetStudentAttendanceReportData(int studentId, int year){
        try{
            List<Object[]> results = attemptRepo.GetStudentAttendanceReportData(studentId,year);
            List<IncomeReportDTO> incomeReportDTOS = new ArrayList<>();
            for(Object[] obj : results){
                String courseCode = (String) obj[0];
                int janAmount = (int) obj[1];
                int febAmount = (int) obj[2];
                int marAmount = (int) obj[3];
                int aprAmount = (int)  obj[4];
                int mayAmount = (int) obj[5];
                int junAmount = (int) obj[6];
                int julAmount = (int) obj[7];
                int augAmount = (int) obj[8];
                int sepAmount = (int) obj[9];
                int octAmount = (int) obj[10];
                int novAmount = (int) obj[11];
                int decAmount = (int) obj[12];
                int total = (int) obj[13];

                IncomeReportDTO incomeReportDTO = new IncomeReportDTO();

                incomeReportDTO.setDescriptiopn(courseCode);
                incomeReportDTO.setJanAmount(janAmount);
                incomeReportDTO.setFebAmount(febAmount);
                incomeReportDTO.setMarAmount(marAmount);
                incomeReportDTO.setAprAmount(aprAmount);
                incomeReportDTO.setMayAmount(mayAmount);
                incomeReportDTO.setJunAmount(junAmount);
                incomeReportDTO.setJulAmount(julAmount);
                incomeReportDTO.setAugAmount(augAmount);
                incomeReportDTO.setSepAmount(sepAmount);
                incomeReportDTO.setSepAmount(octAmount);
                incomeReportDTO.setNovAmount(novAmount);
                incomeReportDTO.setDecAmount(decAmount);
                incomeReportDTO.setTotal(total);

                incomeReportDTOS.add(incomeReportDTO);
            }

            return incomeReportDTOS;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<IncomeReportDTO> GetIncomeReportData(int year){
        try{
            List<Object[]> results = attemptRepo.GetIncomeReportData(year);
            List<IncomeReportDTO> incomeReportDTOS = new ArrayList<>();
            for(Object[] obj : results){
                String courseCode = (String) obj[0];
                int janAmount = (int) obj[1];
                int febAmount = (int) obj[2];
                int marAmount = (int) obj[3];
                int aprAmount = (int) obj[4];
                int mayAmount = (int) obj[5];
                int junAmount = (int) obj[6];
                int julAmount = (int) obj[7];
                int augAmount = (int) obj[8];
                int sepAmount = (int) obj[9];
                int octAmount = (int) obj[10];
                int novAmount = (int) obj[11];
                int decAmount = (int) obj[12];
                int total = (int) obj[13];

                IncomeReportDTO incomeReportDTO = new IncomeReportDTO();

                incomeReportDTO.setDescriptiopn(courseCode);
                incomeReportDTO.setJanAmount(janAmount);
                incomeReportDTO.setFebAmount(febAmount);
                incomeReportDTO.setMarAmount(marAmount);
                incomeReportDTO.setAprAmount(aprAmount);
                incomeReportDTO.setMayAmount(mayAmount);
                incomeReportDTO.setJunAmount(junAmount);
                incomeReportDTO.setJulAmount(julAmount);
                incomeReportDTO.setAugAmount(augAmount);
                incomeReportDTO.setSepAmount(sepAmount);
                incomeReportDTO.setSepAmount(octAmount);
                incomeReportDTO.setNovAmount(novAmount);
                incomeReportDTO.setDecAmount(decAmount);
                incomeReportDTO.setTotal(total);

                incomeReportDTOS.add(incomeReportDTO);
            }

            return incomeReportDTOS;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<ManagerQuizReportDTO> GetManagerQuizReportData(){
        try{
            List<Object[]> results = attemptRepo.GetManagerQuizReportData();
            List<ManagerQuizReportDTO> managerQuizReportDTOS = new ArrayList<>();

            for(Object[] obj : results){
                String courseCode = (String) obj[0];
                String courseName = (String) obj[1];
                int quizCount = (int) obj[2];
                int attemptCount = (int) obj[3];
                int noOfAPasses = (int) obj[4];
                int noOfBPasses = (int) obj[5];
                int noOfCPasses = (int) obj[6];
                int noOfSPasses = (int) obj[7];
                int noOfFPasses = (int) obj[8];

                ManagerQuizReportDTO managerQuizReportDTO = new ManagerQuizReportDTO();
                managerQuizReportDTO.setAttemptCount(attemptCount);
                managerQuizReportDTO.setCourseCode(courseCode);
                managerQuizReportDTO.setCourseName(courseName);
                managerQuizReportDTO.setNoOfAPasses(noOfAPasses);
                managerQuizReportDTO.setNoOfBPasses(noOfBPasses);
                managerQuizReportDTO.setNoOfCPasses(noOfCPasses);
                managerQuizReportDTO.setNoOfSPasses(noOfSPasses);
                managerQuizReportDTO.setNoOfFPasses(noOfFPasses);
                managerQuizReportDTO.setQuizCount(quizCount);


                managerQuizReportDTOS.add(managerQuizReportDTO);
            }

            return managerQuizReportDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<TeacherQuizReportDTO> GetTeacherQuizReportData(int teacherId){
        try{
            List<Object[]> results = attemptRepo.GetTeacherQuizReportData(teacherId);
            List<TeacherQuizReportDTO>teacherQuizReportDTOS = new ArrayList<>();

            for(Object[] obj : results){
                String quizName = (String) obj[0];
                String courseCode = (String) obj[1];
                String courseName = (String) obj[2];
                int averageMarks = (int) obj[3];
                int noOfAttempts = (int) obj[4];

                TeacherQuizReportDTO teacherQuizReportDTO = new TeacherQuizReportDTO();
                teacherQuizReportDTO.setQuizName(quizName);
                teacherQuizReportDTO.setCourseCode(courseCode);
                teacherQuizReportDTO.setCourseName(courseName);
                teacherQuizReportDTO.setAverageMarks(averageMarks);
                teacherQuizReportDTO.setNoOfAttempts(noOfAttempts);

                teacherQuizReportDTOS.add(teacherQuizReportDTO);
            }

            return teacherQuizReportDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Attempt addQuizAttempt(AttemptDTO attemptDTO){
        try{
            Attempt attempt = modelMapper.map(attemptDTO, Attempt.class);
            List<StudentAnswer> studentAnswers = new ArrayList<>();

            for(StudentAnswer studentAnswer : attempt.getStudentAnswers()) {
                studentAnswer.setAttempt(attempt);
                studentAnswers.add(studentAnswer);
            }

            attempt.setStudentAnswers(studentAnswers);
            Attempt attempt1 =  attemptRepo.save(attempt);
            return attempt1;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
