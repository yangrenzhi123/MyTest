package com.yang.test.java.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class AlanOAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlanOAuthApplication.class, args);
	}
}