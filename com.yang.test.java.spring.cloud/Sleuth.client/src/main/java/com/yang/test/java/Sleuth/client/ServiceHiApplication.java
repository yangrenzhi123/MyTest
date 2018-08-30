package com.yang.test.java.Sleuth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceHiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceHiApplication.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/hi")
	public String callHome() {
		return "123";
	}

	@RequestMapping("/hiEnd")
	public String info() {
		return "i'm service-hi hiEnd()";
	}
}