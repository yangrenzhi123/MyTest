package com.yang.test.java.springboot;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
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

//	server:
//		  port: 3106
//		  tomcat:
//		    basedir: /tmp

	@Bean
	MultipartConfigElement multipartConfigElement() {
		File file = new File("/tmp");
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation("/tmp");
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(TestMultipartFile.class, args);
	}
}