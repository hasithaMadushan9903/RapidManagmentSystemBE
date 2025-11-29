package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ADAccountDTO;
import com.rapidattendencesystem.project.dto.LoginDetailsDTO;
import com.rapidattendencesystem.project.dto.PrivilagesDTO;
import com.rapidattendencesystem.project.dto.StudentDTO;
import com.rapidattendencesystem.project.entity.*;
import com.rapidattendencesystem.project.repo.*;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.security.MD5Encoder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ADAccountService {

    @Autowired
    private ADAccountRepo adAccountRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private FrontDeskRepo frontDeskRepo;

    @Autowired
    private SystemAdminRepo systemAdminRepo;

    @Autowired
    private PrivilagesRepo privilagesRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ADAccount updateADAccount(ADAccountDTO adAccountDTO){
        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(adAccountDTO.getPassWord().getBytes());
            byte[] encodedPswd = md.digest();
            BigInteger bigInt = new BigInteger(1,encodedPswd);
            String hashtext = bigInt.toString(16);
            adAccountDTO.setPassWord(hashtext);
            ADAccount A1 = modelMapper.map(adAccountDTO , ADAccount.class);

            ADAccount A2 = adAccountRepo.save(A1);
            System.out.println("User Account Updated");
            return A2;
        }catch (Exception e){
            ADAccount A1 = modelMapper.map(adAccountDTO , ADAccount.class);
            System.out.println(e.getMessage());
            return A1;
        }
    }

    public ADAccount updateProfilePicture(String profilePictureName, String userCode){
        try{
            int noOfUpdatedRecords = adAccountRepo.updateProfilePicture(profilePictureName,userCode);
            if(noOfUpdatedRecords >= 1){
                return adAccountRepo.findByUserCode(userCode);
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ADAccount createUserAccount(ADAccountDTO adAccountDTO){
        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(adAccountDTO.getPassWord().getBytes());
            byte[] encodedPswd = md.digest();
            BigInteger bigInt = new BigInteger(1,encodedPswd);
            String hashtext = bigInt.toString(16);
            adAccountDTO.setPassWord(hashtext);
            ADAccount A1 = modelMapper.map(adAccountDTO , ADAccount.class);
            A1.setCreatedDateTime(LocalDateTime.now());
            if(A1.getId()>0){
                System.out.println("User Account Already Created");
                return A1;
            }else{
                ADAccount A2 = adAccountRepo.save(A1);
                System.out.println("User Account Created");
               return A2;
            }
        }catch (Exception e){
            ADAccount A1 = modelMapper.map(adAccountDTO , ADAccount.class);
            System.out.println(e.getMessage());
            return A1;
        }
    }

    public ADAccount getALogin(ADAccountDTO adAccountDTO){
        try{
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(adAccountDTO.getPassWord().getBytes());
            byte[] encodedPswd = md.digest();
            BigInteger bigInt = new BigInteger(1,encodedPswd);
            String hashtext = bigInt.toString(16);

            String usercode = adAccountDTO.getUserCode();

            ADAccount adAccount = adAccountRepo.findByUserCodeAndPassWord(usercode,hashtext);

            return adAccount;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public LoginDetailsDTO checkLogin(ADAccountDTO adAccountDTO){
        try{
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(adAccountDTO.getPassWord().getBytes());
            byte[] encodedPswd = md.digest();
            BigInteger bigInt = new BigInteger(1,encodedPswd);
            String hashtext = bigInt.toString(16);

            String usercode = adAccountDTO.getUserCode();

            ADAccount adAccount = adAccountRepo.findByUserCodeAndPassWord(usercode,hashtext);
            if(adAccount.getId()>0){
                LoginDetailsDTO loginDetailsDTO = loginDetails(usercode);
                return loginDetailsDTO;
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    public LoginDetailsDTO loginDetails(String usercode){
        char fl = usercode.charAt(0);
        ADAccount AD = adAccountRepo.findByUserCode(usercode);
        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
        if(fl == 'S'){
            Student S1 = studentRepo.findStudentByScodeAndIsActive(usercode,true);
            if(!S1.getIsActive()){
                loginDetailsDTO = null;
            }else {
                List<Privilages> P1 = privilagesRepo.findByRoleAndIsActive(S1.getRole(),true);
                List<PrivilagesDTO> P2 = modelMapper.map(P1, new TypeToken<List<PrivilagesDTO>>() {}.getType());
                loginDetailsDTO.setFullName(S1.getFullName());
                loginDetailsDTO.setIsLoginSuccess(true);
                loginDetailsDTO.setId(S1.getId());
                loginDetailsDTO.setUsercode(S1.getScode());
                loginDetailsDTO.setPrivilagesDTO(P2);
                loginDetailsDTO.setProfilePictureName(AD.getProfilePictureName());
                loginDetailsDTO.setGender(S1.getGender());
                loginDetailsDTO.setBirthday(S1.getBirthDay());
                loginDetailsDTO.setJoinedDate(AD.getCreatedDateTime());
                loginDetailsDTO.setEmail(S1.getEmail());
            }

        }else if(fl =='T'){
            Teacher T1 = teacherRepo.findTeacherByTcode(usercode).get();
            if(!T1.getIsActive()){
                loginDetailsDTO = null;
            }else {
                List<Privilages> P1 = privilagesRepo.findByRoleAndIsActive(T1.getRole(),true);
                List<PrivilagesDTO> P2 = modelMapper.map(P1, new TypeToken<List<PrivilagesDTO>>() {}.getType());
                loginDetailsDTO.setFullName(T1.getFullName());
                loginDetailsDTO.setIsLoginSuccess(true);
                loginDetailsDTO.setId(T1.getId());
                loginDetailsDTO.setUsercode(T1.getTcode());
                loginDetailsDTO.setPrivilagesDTO(P2);
                loginDetailsDTO.setProfilePictureName(AD.getProfilePictureName());
                loginDetailsDTO.setGender(T1.getGender());
                loginDetailsDTO.setBirthday(T1.getBirthday());
                loginDetailsDTO.setJoinedDate(AD.getCreatedDateTime());
                loginDetailsDTO.setEmail(T1.getEmail());
            }

        }else if(fl =='M'){
            Manager M1 = managerRepo.findManagerByMcode(usercode);
            if(!M1.getIsActive()){
                loginDetailsDTO = null;
            }else{
                List<Privilages> P1 = privilagesRepo.findByRoleAndIsActive(M1.getRole(),true);
                List<PrivilagesDTO> P2 = modelMapper.map(P1, new TypeToken<List<PrivilagesDTO>>() {}.getType());
                loginDetailsDTO.setFullName(M1.getFullName());
                loginDetailsDTO.setPrivilagesDTO(P2);
                loginDetailsDTO.setIsLoginSuccess(true);
                loginDetailsDTO.setId(M1.getId());
                loginDetailsDTO.setUsercode(M1.getMcode());
                loginDetailsDTO.setProfilePictureName(AD.getProfilePictureName());
                loginDetailsDTO.setGender(M1.getGender());
                loginDetailsDTO.setBirthday(M1.getBirthDay());
                loginDetailsDTO.setJoinedDate(AD.getCreatedDateTime());
                loginDetailsDTO.setEmail(M1.getEmail());
            }

        }else if(fl == 'F'){
            FrontDesk F1 = frontDeskRepo.findFrontDeskByFcode(usercode);
            if(!F1.getIsActive()){
                loginDetailsDTO = null;
            }else{
                List<Privilages> P1 = privilagesRepo.findByRoleAndIsActive(F1.getRole(),true);
                List<PrivilagesDTO> P2 = modelMapper.map(P1, new TypeToken<List<PrivilagesDTO>>() {}.getType());
                loginDetailsDTO.setFullName(F1.getFullName());
                loginDetailsDTO.setUsercode(F1.getFcode());
                loginDetailsDTO.setIsLoginSuccess(true);
                loginDetailsDTO.setId(F1.getId());
                loginDetailsDTO.setPrivilagesDTO(P2);
                loginDetailsDTO.setProfilePictureName(AD.getProfilePictureName());
                loginDetailsDTO.setGender(F1.getGender());
                loginDetailsDTO.setBirthday(F1.getBirthDay());
                loginDetailsDTO.setJoinedDate(AD.getCreatedDateTime());
                loginDetailsDTO.setEmail(F1.getEmail());
            }

        }else if(fl == 'A'){
            SystemAdmin A1 = systemAdminRepo.findSystemAdminBySyscode(usercode);
            if(!A1.getIsActive()){
                loginDetailsDTO = null;
            }else{
                List<Privilages> P1 = privilagesRepo.findByRoleAndIsActive(A1.getRole(),true);
                List<PrivilagesDTO> P2 = modelMapper.map(P1, new TypeToken<List<PrivilagesDTO>>() {}.getType());
                loginDetailsDTO.setFullName(A1.getFullName());
                loginDetailsDTO.setUsercode(A1.getSyscode());
                loginDetailsDTO.setIsLoginSuccess(true);
                loginDetailsDTO.setId(A1.getId());
                loginDetailsDTO.setPrivilagesDTO(P2);
                loginDetailsDTO.setProfilePictureName(AD.getProfilePictureName());
                loginDetailsDTO.setGender(A1.getGender());
                loginDetailsDTO.setBirthday(A1.getBirthDay());
                loginDetailsDTO.setJoinedDate(AD.getCreatedDateTime());
                loginDetailsDTO.setEmail(A1.getEmail());
            }
        }else{
            loginDetailsDTO.setIsLoginSuccess(false);
        }
        return loginDetailsDTO;
    }

}
