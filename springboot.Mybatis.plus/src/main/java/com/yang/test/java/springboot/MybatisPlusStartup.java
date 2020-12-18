package com.yang.test.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.springboot.mybatis.intercept.MybatisSqlInterceptor;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class MybatisPlusStartup {

	@Bean
	public MybatisSqlInterceptor mybatisSqlInterceptor() {
		return new MybatisSqlInterceptor();
	}

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusStartup.class, args);
	}
}