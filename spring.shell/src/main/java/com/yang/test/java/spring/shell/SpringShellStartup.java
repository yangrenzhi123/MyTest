package com.yang.test.java.spring.shell;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShellStartup {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringShellStartup.class, args);
	}
}