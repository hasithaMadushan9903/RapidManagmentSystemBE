package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.MonthWiseIncome;
import com.rapidattendencesystem.project.repo.ClassFeeCourseRepo;
import com.rapidattendencesystem.project.repo.TeacherPaymentCourseRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherPaymentCourseService {
    @Autowired
    private TeacherPaymentCourseRepo teacherPaymentCourseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<MonthWiseIncome> getTeacherPaymentBymonth(int year){
        try{
            List<Object[]> counts = teacherPaymentCourseRepo.getTeacherPaymentByMonth(year);
            List<MonthWiseIncome> monthWiseIncomes = new ArrayList<>();
            BigDecimal zero = new BigDecimal("0.00");

            for (Object[] count : counts){
                MonthWiseIncome dto = new MonthWiseIncome();
                if (count[0] != null) {
                    dto.setMonthId((int) count[0]);
                } else {
                    dto.setMonthId(0);
                }
                if (count[1] != null) {
                    dto.setAmmount((BigDecimal) count[1]);
                } else {
                    dto.setAmmount((BigDecimal) zero);
                }

                monthWiseIncomes.add(dto);
            }

            return monthWiseIncomes;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
