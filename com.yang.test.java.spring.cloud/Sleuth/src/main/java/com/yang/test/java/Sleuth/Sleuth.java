package com.yang.test.java.Sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class Sleuth {
	public static void main(String[] args) {
		SpringApplication.run(Sleuth.class, args);
	}
}