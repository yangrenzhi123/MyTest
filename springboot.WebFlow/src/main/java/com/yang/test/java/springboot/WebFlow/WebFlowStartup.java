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

	static String result;
	static {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<100;i++) {
			sb.append("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		}
		result = sb.toString();
	}
	
	@GetMapping("/1")
	public Mono<String> hello() {
		return Mono.just(result);
	}

	@GetMapping("/2")
	public String hello2() {
		return result;
	}
}