package com.rapidattendencesystem.project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class RapidattendencesystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(RapidattendencesystemApplication.class, args);
		System.out.println("Spring Boot Succussfully run");
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
