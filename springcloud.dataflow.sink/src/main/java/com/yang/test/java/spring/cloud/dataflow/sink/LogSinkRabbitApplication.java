package com.yang.test.java.spring.cloud.dataflow.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.app.log.sink.LogSinkConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ LogSinkConfiguration.class })
public class LogSinkRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogSinkRabbitApplication.class, args);
	}
}