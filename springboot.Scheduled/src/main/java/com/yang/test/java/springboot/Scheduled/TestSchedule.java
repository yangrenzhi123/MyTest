package com.yang.test.java.springboot.Scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TestSchedule {
	public static void main(String[] args) {
		SpringApplication.run(TestSchedule.class, args);
	}
}