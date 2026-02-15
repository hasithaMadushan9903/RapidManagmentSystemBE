package com.rapidattendencesystem.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/adaccountctrl/checklogin",
                                "/api/v1/enrolcoursectrl/getenrolcourse",
                                "/api/v1/gradectrl/getgrades",
                                "/api/v1/parentctrl/getparentbyid/**",
                                "/api/v1/studentctrl/addstudent",
                                "/api/v1/enrolmentctrl/addEnrolment",
                                "/api/v1/classfeectrl/addclassfee",
                                "/api/v1/monthctrl/getmonths",
                                "/api/v1/rolectrl/getroles",
                                "/api/v1/coursectrl/getcourses",
                                "/api/v1/adaccountctrl/createuseraccount",
                                "/api/v1/payherectrl/init/**",
                                "/api/v1/payherectrl/veryfypayment/**",
                                "/api/v1/payherectrl/notify",
                                "/api/v1/classfeectrl/getLastreciptnumber",
                                "/authenticate").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Add JWT filter before Spring's authentication filter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
