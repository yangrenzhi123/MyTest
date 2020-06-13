package com.yang.test.java.springboot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.springboot.entity.Man;

@SpringBootApplication
public class Springboot {

	@Bean(name = "t1")
	public Man test() {
		return new Man();
	}

	@Bean(name = "t1")
	public Man test2() {
		return new Man();
	}

	public Man test3() {
		return new Man();
	}

	public Man test4() {
		return new Man();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Springboot.class, args);
	}
}