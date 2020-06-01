package com.yang.test.java.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Value("${test.value}")
	private String testValue;

	@RequestMapping("/test")
	public String index() {
		return testValue;
	}
}