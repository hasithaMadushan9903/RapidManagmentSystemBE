package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.AppIconDTO;
import com.rapidattendencesystem.project.dto.RoleDTO;
import com.rapidattendencesystem.project.entity.AppIcon;
import com.rapidattendencesystem.project.entity.Role;
import com.rapidattendencesystem.project.repo.AppIconRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class AppIconService {

    @Autowired
    private AppIconRepo appIconRepo;

    @Autowired
    private ModelMapper modelMapper;

    public AppIcon createAppIcon(AppIconDTO appIconDTO){
        try {
            AppIcon A1 = modelMapper.map(appIconDTO, AppIcon.class);
            if(appIconRepo.existsById(A1.getId())){
                System.out.println("user exist");
                return A1;
            }else{
                AppIcon A2 = appIconRepo.save(A1);
                System.out.println(A2);
                return A2;
            }
        }catch (Exception e){
            AppIcon A1 = modelMapper.map(appIconDTO, AppIcon.class);
            return A1;
        }
    }

    public List<AppIconDTO> getAppIcon(){
        try{
            return modelMapper.map(appIconRepo.findByIsActive(true), new TypeToken<List<AppIconDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AppIcon updateAppIcon(AppIconDTO appIconDTO){
        try{
            AppIcon a1 = modelMapper.map(appIconDTO , AppIcon.class);
            return appIconRepo.save(a1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
