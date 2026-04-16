package com.example.emailerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailerServiceApplication.class, args);
	}
}