package com.yang.test.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SwaggerStartup {

	/** UI展示地址 /swagger-ui.html */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SwaggerStartup.class, args);
	}
}