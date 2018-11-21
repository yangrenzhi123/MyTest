package com.yang.test.java.springboot.WebFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebFlowStartup {

	public static void main(String[] args) {
		SpringApplication.run(WebFlowStartup.class, args);
	}
}

@RestController
class HelloController {

	@GetMapping("/hello")
	public Mono<Object> hello() {
		return Mono.just("1234567");
	}

	@GetMapping("/hello2")
	public String hello2() {
		return "1234567";
	}
}