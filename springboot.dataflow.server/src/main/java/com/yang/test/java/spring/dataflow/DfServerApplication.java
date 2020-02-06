package com.yang.test.java.spring.dataflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;

@EnableDataFlowServer
@SpringBootApplication
public class DfServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfServerApplication.class, args);
	}
}