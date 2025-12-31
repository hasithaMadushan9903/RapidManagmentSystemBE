package com.rapidattendencesystem.project.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@RequestMapping("api/v1/fileuploadctrl")
@CrossOrigin("*")
public class FileUploadController {

    private static final String UPLOAD_DIR = "C:\\Users\\hasithadar\\Downloads\\RapidInstituteManagmentSystem-master\\Images\\ProfilePicture";

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO> uploadFile(@RequestParam("file") MultipartFile file){
        try{
            if (file.isEmpty()){
                responseDTO.setCode("01");
                responseDTO.setMassage("File Is Empty");
                responseDTO.setContent(null);

                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String fileUrl = uploadResult.get("url").toString();

                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(fileUrl);  // Save URL instead of local file name
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
        }
    }

    private String getExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        return (i > 0) ? fileName.substring(i + 1) : "png";  // default to png
    }
}
