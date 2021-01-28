package com.yang.test.java.spring.framework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yang.AppConfig;
import com.yang.service.UserService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		context.getBean("a", A.class);
		
		UserService a = context.getBean("userService", UserService.class);
		a.selectById();

		context.close();
	}
}