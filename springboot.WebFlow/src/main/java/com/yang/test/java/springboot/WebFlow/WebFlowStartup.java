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
	public Mono<String> hello() {
		return Mono.just("Welcome to reactive world ~");
	}
}