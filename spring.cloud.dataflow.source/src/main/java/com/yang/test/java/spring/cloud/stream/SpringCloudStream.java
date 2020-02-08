package com.yang.test.java.spring.cloud.stream;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudStream {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringCloudStream.class, args);
	}
}