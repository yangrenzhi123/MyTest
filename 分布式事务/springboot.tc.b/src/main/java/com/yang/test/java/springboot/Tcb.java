package com.yang.test.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

@SpringBootApplication
@EnableDistributedTransaction
@MapperScan("com.yang.test.java.springboot.dao")
public class Tcb {

	public static void main(String[] args) {
		SpringApplication.run(Tcb.class, args);
	}
}