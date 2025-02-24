package com.mezzi.carsnav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mezzi.carsnav")
public class CarsnavApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsnavApplication.class, args);
	}

}
