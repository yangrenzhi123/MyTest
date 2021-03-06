package com.yang.test.java.springboot;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

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
	public String index(HttpServletRequest request) throws Exception {
		Enumeration<String> e = request.getHeaderNames();
		while(e.hasMoreElements()) {
			String header = e.nextElement();
			System.out.println(header+"：" + request.getHeader(header));
		}
		System.out.println();

		return "success";
	}
}