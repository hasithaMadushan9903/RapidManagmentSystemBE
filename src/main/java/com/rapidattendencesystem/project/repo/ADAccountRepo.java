package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.ADAccount;
import com.rapidattendencesystem.project.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ADAccountRepo extends JpaRepository<ADAccount, Integer> {
    ADAccount findByUserCodeAndPassWord(String usercode, String password);
    ADAccount findByUserCode(String usercode);

    @Transactional
    @Modifying
    @Query("UPDATE ADAccount SET profilePictureName = :profilePictureName WHERE userCode = :userCode")
    int updateProfilePicture(@Param("profilePictureName") String profilePictureName ,@Param("userCode") String userCode );
}
