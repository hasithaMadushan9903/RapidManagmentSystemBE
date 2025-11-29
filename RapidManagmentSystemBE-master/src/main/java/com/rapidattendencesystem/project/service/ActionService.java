package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ActionDTO;
import com.rapidattendencesystem.project.dto.AppIconDTO;
import com.rapidattendencesystem.project.entity.Action;
import com.rapidattendencesystem.project.entity.AppIcon;
import com.rapidattendencesystem.project.repo.ActionRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ActionService {

    @Autowired
    private ActionRepo actionRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Action createAction(ActionDTO actionDTO){
        try {
            Action A1 = modelMapper.map(actionDTO, Action.class);
            if(actionRepo.existsById(A1.getId())){
                System.out.println("Action exist");
                return A1;
            }else{
                Action A2 = actionRepo.save(A1);
                System.out.println(A2);
                return A2;
            }
        }catch (Exception e){
            Action A1 = modelMapper.map(actionDTO, Action.class);
            return A1;
        }
    }

    public List<ActionDTO> getActions(){
        try{
            return modelMapper.map(actionRepo.findByIsActive(true), new TypeToken<List<ActionDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Action updateAction(ActionDTO actionDTO){
        try{
            Action a1 = modelMapper.map(actionDTO , Action.class);
            return actionRepo.save(a1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
