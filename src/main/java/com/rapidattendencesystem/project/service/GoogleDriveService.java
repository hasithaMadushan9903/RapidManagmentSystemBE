package com.rapidattendencesystem.project.service;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;



@Service
public class GoogleDriveService {

    @Autowired
    private Drive drive;

    @Autowired
    private UploadProgressService progressService;

//    Development Folder
//    private static final String FOLDER_ID = "14BBSD2WIuZ831UL8dnUnGUKz3oLVk7iQ";

//    production folder
    private static final String FOLDER_ID = "1nXiEGTCi_0g-v4014buCb5F2bhNVRwll";


    @Async
    public void uploadFileAsync(java.io.File file, String originalFileName, String contentType, String uploadId) {

        try {
            com.google.api.services.drive.model.File fileMetadata =
                    new com.google.api.services.drive.model.File();

            fileMetadata.setName(originalFileName);
            fileMetadata.setParents(Collections.singletonList(FOLDER_ID));

            FileContent mediaContent = new FileContent(contentType, file);

            Drive.Files.Create request = drive.files()
                    .create(fileMetadata, mediaContent);

            MediaHttpUploader uploader = request.getMediaHttpUploader();

            uploader.setProgressListener(progress -> {

                double percentage = progress.getProgress() * 100;
                progressService.setProgress(uploadId, percentage);

                System.out.println("Upload Progress: " + percentage);
            });

            com.google.api.services.drive.model.File uploadedFile =
                    request.setFields("id, webViewLink").execute();

            // Make public
            Permission permission = new Permission();
            permission.setType("anyone");
            permission.setRole("reader");

            drive.permissions().create(uploadedFile.getId(), permission).execute();

            String fileUrl = uploadedFile.getWebViewLink();

            if (fileUrl != null) {
                progressService.setUrl(uploadId, fileUrl);
            }

            progressService.setProgress(uploadId, 100.0);

            file.delete();

        } catch (Exception e) {
            progressService.setProgress(uploadId, -1);
            e.printStackTrace();
        }
    }

    private java.io.File convertMultiPartToFile(MultipartFile file) throws IOException {

        java.io.File convFile = java.io.File.createTempFile("upload-", ".tmp");
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setParents(Collections.singletonList(FOLDER_ID));

        java.io.File tempFile = java.io.File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);

        FileContent mediaContent = new FileContent(file.getContentType(), tempFile);

        File uploadedFile = drive.files().create(fileMetadata, mediaContent)
                .setFields("id, webViewLink")
                .setSupportsAllDrives(true)
                .execute();

        // 🔥 Make file public
        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        drive.permissions().create(uploadedFile.getId(), permission).execute();
        tempFile.delete();
        return uploadedFile.getWebViewLink();
    }
}