package com.yang.test.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class Swagger {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Swagger.class, args);
	}
}