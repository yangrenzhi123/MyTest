package com.yang.test.java.spring.boot.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestCaffeine {

	public static void main(String[] args) {
		SpringApplication.run(TestCaffeine.class, args);
	}
}