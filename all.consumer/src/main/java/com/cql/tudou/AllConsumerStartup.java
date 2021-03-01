package com.cql.tudou;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AllConsumerStartup implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AllConsumerStartup.class, args);
	}
}

@RestController
class UserController {

	@Reference(version = "default")
	private DemoService demoService;

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		String s = demoService.sayHello("cql_test");
		return s;
	}
}