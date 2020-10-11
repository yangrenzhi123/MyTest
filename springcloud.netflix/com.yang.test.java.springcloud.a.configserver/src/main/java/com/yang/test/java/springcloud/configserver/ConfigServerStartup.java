package com.yang.test.java.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerStartup {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerStartup.class, args);
	}
}