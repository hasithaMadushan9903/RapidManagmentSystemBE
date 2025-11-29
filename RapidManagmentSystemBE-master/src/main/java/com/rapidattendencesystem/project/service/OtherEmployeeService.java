package com.rapidattendencesystem.project.service;


import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.OtherEmployeeDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.entity.*;
import com.rapidattendencesystem.project.repo.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OtherEmployeeService {

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private FrontDeskRepo frontDeskRepo;

    @Autowired
    private SystemAdminRepo systemAdminRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OtherEmployeeDTO> getOtherEmployees(){
        try{
            List<OtherEmployeeDTO> allOtherEmployees = new ArrayList<>();
            List<OtherEmployeeDTO> otherEmployees = modelMapper.map(frontDeskRepo.findByIsActiveOrderByIdDesc(true), new TypeToken<List<OtherEmployeeDTO>>() {}.getType()) ;
            allOtherEmployees = getAllOtherEmployees(allOtherEmployees,otherEmployees);


            otherEmployees = modelMapper.map(managerRepo.findByIsActiveOrderByIdDesc(true), new TypeToken<List<OtherEmployeeDTO>>() {}.getType()) ;
            allOtherEmployees = getAllOtherEmployees(allOtherEmployees,otherEmployees);

            otherEmployees = modelMapper.map(systemAdminRepo.findByIsActiveOrderByIdDesc(true), new TypeToken<List<OtherEmployeeDTO>>() {}.getType()) ;
            allOtherEmployees = getAllOtherEmployees(allOtherEmployees,otherEmployees);

            return allOtherEmployees;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<OtherEmployeeDTO> getAllOtherEmployees(List<OtherEmployeeDTO> allOtherEmployees , List<OtherEmployeeDTO> otherEmployees){
        allOtherEmployees.addAll(otherEmployees);
        return allOtherEmployees;
    }

    public OtherEmployeeDTO updateOrDeleteEmployee(OtherEmployeeDTO otherEmployeeDTO){
        try{
            if(otherEmployeeDTO.getRoleId() == 2){
                Role r = roleRepo.findRoleById(otherEmployeeDTO.getRoleId());
                FrontDesk frontDesk = modelMapper.map(otherEmployeeDTO , FrontDesk.class);
                frontDesk.setRole(r);
                FrontDesk f1 = frontDeskRepo.save(frontDesk);
                OtherEmployeeDTO o1 = modelMapper.map(f1,OtherEmployeeDTO.class);
                return o1;
            }else if(otherEmployeeDTO.getRoleId() == 3){
                Role r = roleRepo.findRoleById(otherEmployeeDTO.getRoleId());
                Manager manager = modelMapper.map(otherEmployeeDTO , Manager.class);
                manager.setRole(r);
                Manager m1 = managerRepo.save(manager);
                OtherEmployeeDTO o2 = modelMapper.map(m1,OtherEmployeeDTO.class);
                return o2;
            }else {
                Role r = roleRepo.findRoleById(otherEmployeeDTO.getRoleId());
                SystemAdmin sysadmin = modelMapper.map(otherEmployeeDTO , SystemAdmin.class);
                sysadmin.setRole(r);
                SystemAdmin s1 = systemAdminRepo.save(sysadmin);
                OtherEmployeeDTO o3 = modelMapper.map(s1,OtherEmployeeDTO.class);
                return o3;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public OtherEmployeeDTO addEmployee(OtherEmployeeDTO otherEmployeeDTO){
        try {
            if(otherEmployeeDTO.getRoleId() == 2){
                Role r = roleRepo.findRoleById(otherEmployeeDTO.getRoleId());
                FrontDesk frontDesk = modelMapper.map(otherEmployeeDTO , FrontDesk.class);
                frontDesk.setRole(r);
                FrontDesk f1 = frontDeskRepo.save(frontDesk);
                OtherEmployeeDTO o1 = modelMapper.map(f1,OtherEmployeeDTO.class);
                return o1;
            }else if(otherEmployeeDTO.getRoleId() == 3){
                Role r = roleRepo.findRoleById(otherEmployeeDTO.getRoleId());
                Manager manager = modelMapper.map(otherEmployeeDTO , Manager.class);
                manager.setRole(r);
                Manager m1 = managerRepo.save(manager);
                OtherEmployeeDTO o2 = modelMapper.map(m1,OtherEmployeeDTO.class);
                return o2;
            }else {
                Role r = roleRepo.findRoleById(otherEmployeeDTO.getRoleId());
                SystemAdmin sysadmin = modelMapper.map(otherEmployeeDTO , SystemAdmin.class);
                sysadmin.setRole(r);
                SystemAdmin s1 = systemAdminRepo.save(sysadmin);
                OtherEmployeeDTO o3 = modelMapper.map(s1,OtherEmployeeDTO.class);
                return o3;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
