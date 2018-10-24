package com.yang.test.java.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class TestMultipartFile {

	@Configuration
	public class MyWebAppConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/download/**").addResourceLocations("file:/tmp/");
			super.addResourceHandlers(registry);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(TestMultipartFile.class, args);
	}
}