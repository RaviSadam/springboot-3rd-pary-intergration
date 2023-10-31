package com.springboot.wetherapp;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class WetherappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetherappApplication.class, args);
	}

	@Bean 
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}
	@Bean
	public SimpleDateFormat simpleDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

}
