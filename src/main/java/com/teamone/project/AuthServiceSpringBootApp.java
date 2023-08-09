package com.teamone.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.teamone.project", "com.teamone.project.service"})
public class AuthServiceSpringBootApp {
	
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceSpringBootApp.class, args);
	}
	
}