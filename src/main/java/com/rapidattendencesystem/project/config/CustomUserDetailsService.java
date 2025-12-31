package com.rapidattendencesystem.project.config;

import com.rapidattendencesystem.project.entity.ADAccount;
import com.rapidattendencesystem.project.repo.ADAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ADAccountRepo adAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        ADAccount adAccount = adAccountRepo.findByUserCode(userCode);
        if (adAccount == null) {
            throw new UsernameNotFoundException("User not found with code: " + userCode);
        }

        return new User(adAccount.getUserCode(), adAccount.getPassWord(), new ArrayList<>());
    }
}
