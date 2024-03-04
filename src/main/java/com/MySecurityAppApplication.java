package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecurityAppApplication.class, args);
		
		System.err.println("Application is Running...");
	}

}
