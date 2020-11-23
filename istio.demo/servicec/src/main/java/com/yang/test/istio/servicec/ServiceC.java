package com.yang.test.istio.servicec;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServiceC {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ServiceC.class, args);
	}
}

@RestController
class TestController {

	@RequestMapping("/")
	public String index() throws Exception {
		System.out.println("successc");
		return "successc";
	}
}