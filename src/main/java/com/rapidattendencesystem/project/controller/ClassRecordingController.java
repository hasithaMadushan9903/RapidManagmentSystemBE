package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ClassRecordingDTO;
import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.service.ClassRecordingService;
import com.rapidattendencesystem.project.service.CourseService;
import com.rapidattendencesystem.project.service.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/classrecctrl")
@CrossOrigin("*")
public class ClassRecordingController {

    @Autowired
    private ClassRecordingService classRecordingService;

    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addClassRec")
    public ResponseEntity<ResponseDTO> addClassRec(@RequestBody ClassRecordingDTO classRecordingDTO){
        try {
            ClassRecordingDTO C1 = classRecordingService.createClassRecording(classRecordingDTO);
            if(C1.getId() > 0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Class Recording Inserted");
                responseDTO.setContent(C1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(classRecordingDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO> uploadFile(@RequestParam("file") MultipartFile file){
        System.out.println("UPLOAD API HIT"); // ✅ ADD THIS LINE
        try{

            String uploadId = UUID.randomUUID().toString();
            java.io.File tempFile = java.io.File.createTempFile("upload_", "_" + file.getOriginalFilename());
            file.transferTo(tempFile);
            googleDriveService.uploadFileAsync(tempFile, file.getOriginalFilename(), file.getContentType(), uploadId);

            responseDTO.setCode("00");
            responseDTO.setMassage("Upload started");
            responseDTO.setContent(uploadId);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getcourseclassrec/{courseId}")
    public ResponseEntity<ResponseDTO> getCourseClassRec(@PathVariable int courseId){
        try {
            List<ClassRecordingDTO> classRecordingDTOS = classRecordingService.getCourseClassRecording(courseId);
            if(!classRecordingDTOS.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(classRecordingDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);

            }else {
                responseDTO.setCode("01");
                responseDTO.setMassage("Courses Are Empty");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
