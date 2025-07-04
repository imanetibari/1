package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example", "com.example.rcontroller"})
@EnableJpaRepositories("com.example.repository")
@EntityScan("com.example.entity")
public class P5Application {

	public static void main(String[] args) {
		SpringApplication.run(P5Application.class, args);
	}

}
	