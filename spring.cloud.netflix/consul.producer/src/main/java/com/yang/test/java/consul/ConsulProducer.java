package com.yang.test.java.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulProducer {

	@RequestMapping("/")
	public String home() {
		return "Hello World";
	}
	

	@RequestMapping("/1")
	public String home1() {
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulProducer.class, args);
	}
}