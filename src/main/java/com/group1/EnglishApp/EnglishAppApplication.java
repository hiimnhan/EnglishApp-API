package com.group1.EnglishApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EnglishAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglishAppApplication.class, args);
	}

}
