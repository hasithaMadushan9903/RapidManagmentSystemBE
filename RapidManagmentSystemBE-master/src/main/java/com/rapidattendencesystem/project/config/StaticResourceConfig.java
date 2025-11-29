package com.rapidattendencesystem.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the URL path "/images/**" to a folder in your file system
        String uploadFolder = Paths.get("C:/Users/hasithadar/Downloads/RapidInstituteManagmentSystem-master/Images/ProfilePicture/").toUri().toString();

        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadFolder);
    }
}
