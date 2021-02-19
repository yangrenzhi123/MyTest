package com.yang.test.java.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Value("${test:null}")
	private String username;

	@RequestMapping("/")
	@ResponseBody
	public String getUser() {
		return username;
	}
}