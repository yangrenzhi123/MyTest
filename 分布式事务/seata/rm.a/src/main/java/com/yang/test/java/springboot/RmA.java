package com.yang.test.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;

@SpringBootApplication//(exclude = {SeataAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.yang.test.java.springboot.dao")
public class RmA {

	public static void main(String[] args) {
		SpringApplication.run(RmA.class, args);
	}
}