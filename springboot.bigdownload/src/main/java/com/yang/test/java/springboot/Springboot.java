package com.yang.test.java.springboot;

import java.io.IOException;

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

	public static final byte[] data = new byte[1024*1024];
	static {
		for(int i=0;i<data.length;i++) {
			data[i] = 65;
		}
		System.out.println("數據初始化完成");
	}

	@RequestMapping("/")
	public byte[] index() {
		return data;
	}
}