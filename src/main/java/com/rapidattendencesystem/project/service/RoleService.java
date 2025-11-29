package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.RoleDTO;
import com.rapidattendencesystem.project.entity.Grade;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Role;
import com.rapidattendencesystem.project.repo.RoleRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Role createRole(RoleDTO roleDTO){
        try {
            Role R1 = modelMapper.map(roleDTO, Role.class);
            if(roleRepo.existsById(R1.getId())){
                System.out.println("user exist");
                return R1;
            }else{
                Role R2 = roleRepo.save(R1);
                System.out.println(R2);
                return R2;
            }
        }catch (Exception e){
            Role R1 = modelMapper.map(roleDTO, Role.class);
            return R1;
        }
    }

    public List<RoleDTO> getRoles(){
        try{
            return modelMapper.map(roleRepo.findByIsActive(true), new TypeToken<List<RoleDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Role updateRole(RoleDTO roleDTO){
        try{
            Role r1 = modelMapper.map(roleDTO , Role.class);
            return roleRepo.save(r1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
