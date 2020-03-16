package org.springframework.cloud.stream.app.http.source.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.app.http.source.HttpSourceConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ HttpSourceConfiguration.class })
public class HttpSourceRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpSourceRabbitApplication.class, args);
	}
}