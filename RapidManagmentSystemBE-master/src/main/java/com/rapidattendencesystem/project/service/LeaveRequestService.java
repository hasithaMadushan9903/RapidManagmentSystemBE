package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ApprovingStatusDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.LeaveRequestDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.entity.ApprovingStatus;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.LeaveRequest;
import com.rapidattendencesystem.project.repo.LeaveRequestRepo;
import com.rapidattendencesystem.project.repo.MonthRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    @Autowired
    private ModelMapper modelMapper;

    public LeaveRequest createLeaveRequest(LeaveRequestDTO leaveRequestDTO){
        try {
            LeaveRequest L1 = modelMapper.map(leaveRequestDTO, LeaveRequest.class);
            L1.setCreatedDateTime(LocalDateTime.now());
            if(leaveRequestRepo.existsById(L1.getId())){
                System.out.println("Request exist");
                return L1;
            }else{
                LeaveRequest L2 = leaveRequestRepo.save(L1);
                System.out.println(L2);
                return L2;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            LeaveRequest L1 = modelMapper.map(leaveRequestDTO, LeaveRequest.class);
            return L1;
        }
    }

    public LeaveRequest updateOrDeleteLeaveRequest(LeaveRequestDTO leaveRequestDTO){
        try {
            LeaveRequest L1 = modelMapper.map(leaveRequestDTO, LeaveRequest.class);
            LeaveRequest L2 = leaveRequestRepo.save(L1);
            return L2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            LeaveRequest L1 = modelMapper.map(leaveRequestDTO, LeaveRequest.class);
            return L1;
        }
    }

    public List<LeaveRequestDTO> getLeaveRequest(String usercode){
        try{
            char fl = usercode.charAt(0);
            if(fl == 'T'){
                List<LeaveRequest> l1 = leaveRequestRepo.findByIsActiveAndTeacher_TcodeOrderByIdDesc(true,usercode);
                List<LeaveRequestDTO> l2 = modelMapper.map(l1 , new TypeToken<List<LeaveRequestDTO>>() {}.getType());

                return l2;
            }else{
                List<LeaveRequest> l1 = leaveRequestRepo.findByIsActiveOrderByCreatedDateTimeDesc(true);
                List<LeaveRequestDTO> l2 = modelMapper.map(l1 , new TypeToken<List<LeaveRequestDTO>>() {}.getType());

                return l2;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<LeaveRequestDTO> getLeaveRequestByApprovingStatus(ApprovingStatusDTO approvingStatusDTO){
        try{
            ApprovingStatus a1 = modelMapper.map(approvingStatusDTO, ApprovingStatus.class);
            List<LeaveRequest> l1 = leaveRequestRepo.findLeaveRequestByApprovingStatusAndIsActiveOrderByCreatedDateTimeDesc(a1,true);
            List<LeaveRequestDTO> l2 = modelMapper.map(l1 , new TypeToken<List<LeaveRequestDTO>>() {}.getType());

            return l2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
