package com.rapidattendencesystem.project.controller;

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

@RestController
@RequestMapping("api/v1/fileuploadctrl")
@CrossOrigin("*")
public class FileUploadController {

    private static final String UPLOAD_DIR = "C:\\Users\\hasithadar\\Downloads\\RapidInstituteManagmentSystem-master\\Images\\ProfilePicture";

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
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                int index = 1;
                String extension = getExtension(file.getOriginalFilename());
                File destinationFile;

                do {
                    destinationFile = new File(UPLOAD_DIR, "image" + index + "." + extension);
                    index++;
                } while (destinationFile.exists());

                Files.copy(file.getInputStream(), destinationFile.toPath());

                String savedFileName = destinationFile.getName();

                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(savedFileName);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
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
