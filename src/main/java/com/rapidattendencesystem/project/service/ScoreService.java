package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ScoreDTO;
import com.rapidattendencesystem.project.entity.Attempt;
import com.rapidattendencesystem.project.entity.Question;
import com.rapidattendencesystem.project.entity.Score;
import com.rapidattendencesystem.project.entity.StudentAnswer;
import com.rapidattendencesystem.project.repo.AnswersRepo;
import com.rapidattendencesystem.project.repo.ScoreRepo;
import com.rapidattendencesystem.project.repo.SubjectRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ScoreService {

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AnswersRepo answersRepo;

    public ScoreDTO addScore(Attempt attempt){
        try{
            Score score = new Score();
            int marks = 0;
            int totalWeight = 0;

            score.setAttempt(attempt);
            score.setStudent(attempt.getStudent());
            score.setQuizzes(attempt.getQuizzes());

            for(StudentAnswer studentAnswer : attempt.getStudentAnswers()){
                Boolean isCorrectAnswer = answersRepo.existsByIdAndIsCorrect(studentAnswer.getAnswers().getId(),true);
                if(isCorrectAnswer){
                    int weightedMark = studentAnswer.getQuestion().getWight();
                    marks = marks + weightedMark;
                }

                totalWeight = totalWeight + studentAnswer.getQuestion().getWight();
            }
            score.setScore(marks);
            score.setTotalWeight(totalWeight);
            Score score1 = scoreRepo.save(score);
            return modelMapper.map(score1 , ScoreDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
