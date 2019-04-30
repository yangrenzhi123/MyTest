package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Springboot {

	public static void main(String[] args) {
		SpringApplication.run(Springboot.class, args);
	}
}

@RestController
class HelloController {

	@RequestMapping("/")
	public void index() throws InterruptedException {
		new TestDahua().doIt();
	}
}