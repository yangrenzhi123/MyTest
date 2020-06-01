package com.yang.test.java.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/")
	public int index() {
		return 1;
	}

	@RequestMapping({ "/g1", "/g2" })
	public String success() {
		return "i am springboot.1";
	}
}