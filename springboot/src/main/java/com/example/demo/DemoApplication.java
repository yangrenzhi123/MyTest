package com.example.demo;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class HelloController {

	@GetMapping("/")
	public String hello2() {
		return "123456";
	}
}