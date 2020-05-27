package com.yang.test.java.springboot;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication
public class Springboot {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Springboot.class, args);
	}
}

@RestController
class HelloController {

	@RequestMapping("/")
	public int index() {
		return GetAllThread.countByStartwith("http-nio-8080-exec-");
	}

	@RequestMapping("/1")
	public String success() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "success";
	}
}