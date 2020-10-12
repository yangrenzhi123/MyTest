package com.yang.test.java.springcloud.txmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagerServer
public class TxManagerStartup {

	public static void main(String[] args) {
		SpringApplication.run(TxManagerStartup.class, args);
	}
}