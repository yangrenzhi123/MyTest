package com.example.demo;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication
public class Springboot {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		SpringApplication.run(Springboot.class, args);
	}
}

@RestController
class HelloController {

	@RequestMapping("/")
	public String index() {
		return "2";
	}
}