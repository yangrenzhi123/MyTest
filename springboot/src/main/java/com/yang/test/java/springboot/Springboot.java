package com.yang.test.java.springboot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Springboot {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Springboot.class, args);
	}
}

@RestController
class TestController {

	@RequestMapping("/")
	public String index() throws Exception {
		return "success";
	}

	@RequestMapping("/alert")
	public String alert() throws Exception {
		System.out.println("alert");
		return "success";
	}
}