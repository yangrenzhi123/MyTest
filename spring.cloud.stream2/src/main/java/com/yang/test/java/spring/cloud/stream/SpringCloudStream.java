package com.yang.test.java.spring.cloud.stream;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.yang.test.java.spring.cloud.stream.inter.MyProcessor;

@SpringBootApplication
@EnableBinding(value = {MyProcessor.class})
public class SpringCloudStream {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringCloudStream.class, args);
	}
}