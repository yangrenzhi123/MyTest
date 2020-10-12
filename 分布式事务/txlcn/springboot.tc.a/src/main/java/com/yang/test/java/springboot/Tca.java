package com.yang.test.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

@SpringBootApplication
@EnableDistributedTransaction
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.yang.test.java.springboot.dao")
public class Tca {

	public static void main(String[] args) {
		SpringApplication.run(Tca.class, args);
	}
}