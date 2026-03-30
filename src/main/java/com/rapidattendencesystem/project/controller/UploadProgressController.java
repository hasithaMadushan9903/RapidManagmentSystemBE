package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ProgressDataDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.UploadProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/uploadprogressconntroller")
@CrossOrigin("*")
public class UploadProgressController {

    @Autowired
    private UploadProgressService progressService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/progress/{uploadId}")
    public ResponseEntity<ResponseDTO> getProgress(@PathVariable String uploadId) {
        try{
            double progress = progressService.getProgress(uploadId);
            String url = progressService.getUrl(uploadId);
            ProgressDataDTO progressDataDTO = new ProgressDataDTO();
            progressDataDTO.setProgress(progress);
            progressDataDTO.setUrl(url);

            responseDTO.setCode("00");
            responseDTO.setMassage("Success");
            responseDTO.setContent(progressDataDTO);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
