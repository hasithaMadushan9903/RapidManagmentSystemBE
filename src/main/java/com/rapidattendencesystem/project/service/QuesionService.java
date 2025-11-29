package com.rapidattendencesystem.project.service;


import com.rapidattendencesystem.project.dto.QuestionDTO;
import com.rapidattendencesystem.project.entity.Privilages;
import com.rapidattendencesystem.project.entity.Question;
import com.rapidattendencesystem.project.repo.PrivilagesRepo;
import com.rapidattendencesystem.project.repo.QuestionRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class QuesionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<QuestionDTO> createQuestion(List<QuestionDTO> questionDTOs){
        try{
            List<Question> questions = modelMapper.map(questionDTOs, new TypeToken<List<Question>>() {}.getType());
            List<Question> savedQuestions = questionRepo.saveAll(questions);
            return modelMapper.map(savedQuestions, new TypeToken<List<QuestionDTO>>() {}.getType());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
