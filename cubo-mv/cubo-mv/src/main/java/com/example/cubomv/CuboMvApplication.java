package com.example.cubomv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins="localhost:3000")
public class CuboMvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuboMvApplication.class, args);
	}

}
