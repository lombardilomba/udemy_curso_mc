package com.udmc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.udmc.resources"})
public class UdemyCursoMcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyCursoMcApplication.class, args);
	}
	
}
