package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.repo.UserProfileRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserProfileService {
    private static final String UPLOAD_DIR = "C:/Users/hasithadar/Downloads/RapidInstituteManagmentSystem-master/Images/";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserProfileRepo userProfileRepo;


}
