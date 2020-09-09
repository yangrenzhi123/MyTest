package com.yang.test.java.springboot;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class AdminClientStartup {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(AdminClientStartup.class, args);
	}
}

@Controller
class TestController {

	private final static Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/")
	public String test() {
		logger.debug("i am Debug");
		logger.info("i am Info");
		return "test";
	}
}