package com.yang.test.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang.test.java.dao")
public class ShardingTestStartup {

	public static void main(String[] args) {
		SpringApplication.run(ShardingTestStartup.class, args);
	}
}