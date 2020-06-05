package com.yang.test.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.java.springboot.service.TestService;

@RestController
public class TestController {

	@Autowired(required = false)
	TestService testService;

	@RequestMapping("/t1")
	public void success() {
		testService.doTest();
	}
	

	@RequestMapping("/")
	public void index() throws Exception {}
}