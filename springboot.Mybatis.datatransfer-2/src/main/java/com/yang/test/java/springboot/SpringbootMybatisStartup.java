package com.yang.test.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class SpringbootMybatisStartup {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisStartup.class, args);
	}
}