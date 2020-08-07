package com.yang.test.java.springboot;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.springboot.entity.Man;

@SpringBootApplication
public class Springboot {

	@Bean
	public Man test() throws ClassNotFoundException, IOException {
		URL[] urls = new URL[] {new URL("file:/c:/classes/")};
		URLClassLoader cl = new URLClassLoader(urls);
		Class<?> c = cl.loadClass("com.yang.test.java.springboot.controller.TestController");
		System.out.println(c.getPackage());
		cl.close();
		
		return new Man();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Springboot.class, args);
	}
}