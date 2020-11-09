package com.yang.test.istio.serviceb;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServiceB {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ServiceB.class, args);
	}
}

@RestController
class TestController {

	@RequestMapping("/")
	public String index() throws Exception {
		return "successb";
	}
}