package com.example.apidemo.Springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "")
//@EnableAutoConfiguration
//@Configuration
//@ComponentScan
public class SpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootApplication.class, args);

		Double a = Double.valueOf(20);
		double b = 20;
		Double c = Double.valueOf(20);

	}


}
