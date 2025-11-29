package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.*;
import com.rapidattendencesystem.project.repo.AttemptRepo;
import com.rapidattendencesystem.project.repo.CourseRepo;
import com.rapidattendencesystem.project.repo.QuizzeRepo;
import com.rapidattendencesystem.project.repo.ScoreRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class QuizzService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuizzeRepo quizzeRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private AttemptRepo attemptRepo;

    public Quizzes updateOrDeleteQuizee(QuizzeDTO quizzeDTO){
        try{
            List<QuestionDTO> questionDTOSInQuizze = quizzeDTO.getQuestions();
            List<Question> questions = new ArrayList<>();

            for(QuestionDTO questionDTO : questionDTOSInQuizze){
                Question question = modelMapper.map(questionDTO,Question.class);

                List<Answers> answers = new ArrayList<>();

                for(AnswerDTO answerDTO : questionDTO.getAnswers()){
                    Answers answers1 = modelMapper.map(answerDTO,Answers.class);

                    answers1.setQuestion(question);
                    answers.add(answers1);
                }

                question.setAnswers(answers);
                questions.add(question);
            }

            Quizzes quizzes = modelMapper.map(quizzeDTO,Quizzes.class);
            for(Question question : questions){
                question.setQuizzes(quizzes);
            }

            quizzes.setQuestions(questions);
            quizzes.setIsActive(quizzeDTO.getIsActive());

            return quizzeRepo.save(quizzes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<QuizzeDTO> getAllQuizees(){
        try{
            List<Quizzes> quizzes = quizzeRepo.findByIsActiveOrderByIdDesc(true);
            List<QuizzeDTO> quizzeDTOS = modelMapper.map(quizzes, new TypeToken<List<QuizzeDTO>>() {}.getType());
            for(QuizzeDTO quizzeDTO : quizzeDTOS){
                quizzeDTO.setIsStudentAttemp(false);
            }
            return quizzeDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<QuizzeDTO> getAllQuizeesForTeacher(int teacherId){
        try{
            List<Course> teacherEnrolledCourses = courseRepo.findByIsActiveAndTeacher_Id(true,teacherId);
            List<Quizzes> QuizeesForTeacher = new ArrayList<>();

            for(Course cou : teacherEnrolledCourses){
                List<Quizzes> quizzes = quizzeRepo.findByCourses(cou.getId());
                QuizeesForTeacher.addAll(quizzes);
            }

            List<QuizzeDTO> quizzeDTOS = modelMapper.map(QuizeesForTeacher, new TypeToken<List<QuizzeDTO>>() {}.getType());
            for(QuizzeDTO quizzeDTO : quizzeDTOS){
                quizzeDTO.setIsStudentAttemp(false);
            }
            return quizzeDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<QuizzeDTO> getAllQuizeesForStudent(int studentId){
        try{
            List<Course> studentEnrolledCourses = courseRepo.findCoursesByStudentId(studentId,true);
            List<Quizzes> QuizeesForStudent = new ArrayList<>();



            for(Course cou : studentEnrolledCourses){
                List<Object[]> results = quizzeRepo.GetQuizIdsForStudentId(studentId,cou.getId());
                for(Object[] obj : results){
                    int id = (int) obj[0];
                    QuizeesForStudent.add(quizzeRepo.findByIdOrderByIdDesc(id));
                }
            }

            List<QuizzeDTO> quizzeDTOS = modelMapper.map(QuizeesForStudent, new TypeToken<List<QuizzeDTO>>() {}.getType());
            for(QuizzeDTO quizzeDTO : quizzeDTOS){
                Boolean isStudentAttemp = attemptRepo.existsByQuizzes_IdAndStudent_Id(quizzeDTO.getId(), studentId);
                if(isStudentAttemp){
                    List<Score> scores = scoreRepo.findByStudent_IdAndQuizzes_Id(studentId,quizzeDTO.getId());
                    Score score = scores.get(0);
                    ScoreDTO scoreDTO = modelMapper.map(score, ScoreDTO.class);
                    quizzeDTO.setScoreDTO(scoreDTO);
                }
                quizzeDTO.setIsStudentAttemp(isStudentAttemp);
            }
            return quizzeDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Quizzes addQuizze(QuizzeDTO quizzeDTO){
        try{
            List<QuestionDTO> questionDTOSInQuizze = quizzeDTO.getQuestions();
            List<Question> questions = new ArrayList<>();

            for(QuestionDTO questionDTO : questionDTOSInQuizze){
                Question question = modelMapper.map(questionDTO,Question.class);

                List<Answers> answers = new ArrayList<>();

                for(AnswerDTO answerDTO : questionDTO.getAnswers()){
                    Answers answers1 = modelMapper.map(answerDTO,Answers.class);

                    answers1.setQuestion(question);
                    answers.add(answers1);
                }

                question.setAnswers(answers);
                questions.add(question);
            }

            Quizzes quizzes = modelMapper.map(quizzeDTO,Quizzes.class);
            for(Question question : questions){
                question.setQuizzes(quizzes);
            }

            quizzes.setQuestions(questions);
            quizzes.setIsActive(true);

            return quizzeRepo.save(quizzes);


        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
