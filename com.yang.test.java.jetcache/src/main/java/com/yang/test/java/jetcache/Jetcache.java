package com.yang.test.java.jetcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.yang.test.java.jetcache.api.UserService;

@SpringBootApplication
@EnableMethodCache(basePackages = "com.yang.test.java.jetcache")
@EnableCreateCacheAnnotation
public class Jetcache {
	public static void main(String[] args) {
		SpringApplication.run(Jetcache.class);
	}
}

@RestController
class HelloController {

	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public String hello2(@PathVariable ("id") String id) {
		return userService.getUserById(id, id);
	}
}