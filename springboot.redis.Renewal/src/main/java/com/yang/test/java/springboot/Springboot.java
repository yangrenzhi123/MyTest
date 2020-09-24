package com.yang.test.java.springboot;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RedisService redisService;

	@RequestMapping("/")
	public void index() throws Exception {
		try {
			redisService.getDistributedLock("testKey");
			System.out.println(1);
		} finally {
			redisService.releaseDistributedLock("testKey");
		}
	}
}