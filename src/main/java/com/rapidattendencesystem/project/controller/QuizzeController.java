package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.QuizzeDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.dto.RoleDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Quizzes;
import com.rapidattendencesystem.project.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quizzctrl")
@CrossOrigin("*")
public class QuizzeController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private QuizzService quizzService;

    @GetMapping("/getquizzes")
    public ResponseEntity<ResponseDTO> getQuizzes(){
        try{
            List<QuizzeDTO> quizzes = quizzService.getAllQuizees();
            if(!quizzes.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(quizzes);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Quizze Empty");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getquizzesforstudent/{studentId}")
    public ResponseEntity<ResponseDTO> getAllQuizeesForStudent(@PathVariable int studentId){
        try{
            List<QuizzeDTO> quizzes = quizzService.getAllQuizeesForStudent(studentId);
            if(!quizzes.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(quizzes);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Quizze Empty");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getquizzesforteacher/{teacherId}")
    public ResponseEntity<ResponseDTO> getAllQuizeesForTeacher(@PathVariable int teacherId){
        try{
            List<QuizzeDTO> quizzes = quizzService.getAllQuizeesForTeacher(teacherId);
            if(!quizzes.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(quizzes);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Quizze Empty");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addquizz")
    public ResponseEntity<ResponseDTO> createHall(@RequestBody QuizzeDTO quizzeDTO){
        try {
            Quizzes q1 = quizzService.addQuizze(quizzeDTO);
            if(q1 != null){
                responseDTO.setCode("00");
                responseDTO.setMassage("Quizz Inserted");
                responseDTO.setContent(q1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(quizzeDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateordeletequizz")
    public ResponseEntity<ResponseDTO> updateOrDeleteQuizz(@RequestBody QuizzeDTO quizzeDTO){
        try {
            Quizzes q1 = quizzService.updateOrDeleteQuizee(quizzeDTO);
            if(q1 != null){
                responseDTO.setCode("00");
                responseDTO.setMassage("Quizz Inserted");
                responseDTO.setContent(q1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(quizzeDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
