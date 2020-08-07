package com.yang.test.java.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

	@RequestMapping("/test")
	public void index() throws Exception {
	}
}