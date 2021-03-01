package com.yang.test.java.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SwaggerStartup {

	/** UI展示地址 http://localhost:8080/swagger-ui.html */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SwaggerStartup.class, args);
	}
}