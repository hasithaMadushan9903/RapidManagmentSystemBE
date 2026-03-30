package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ClassRecordingDTO;
import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.entity.ClassRecording;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.repo.ClassRecordingRepo;
import com.rapidattendencesystem.project.repo.CourseRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClassRecordingService {

    @Autowired
    private ClassRecordingRepo classRecordingRepo;

    @Autowired
    private ModelMapper modalMapper;

    public ClassRecordingDTO createClassRecording(ClassRecordingDTO classRecordingDTO){
        try {
            ClassRecording C1 = modalMapper.map(classRecordingDTO, ClassRecording.class);
            if(classRecordingRepo.existsById(C1.getId())){
                System.out.println("rec exist");
                return classRecordingDTO;
            }else{
                ClassRecording C2 = classRecordingRepo.save(C1);
                System.out.println(C2);
                return modalMapper.map(C2, ClassRecordingDTO.class);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return classRecordingDTO;
        }
    }

    public List<ClassRecordingDTO> getCourseClassRecording(int courseId){
        try{
            List<ClassRecording> classRecordings = classRecordingRepo.findByCourse_Id(courseId);
            List<ClassRecordingDTO> classRecordingDTOS = modalMapper.map(classRecordings, new TypeToken<List<ClassRecordingDTO>>() {}.getType());
            return classRecordingDTOS;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
