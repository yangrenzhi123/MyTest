package com.example.demo;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Controller {

	@RequestMapping("/")
	public String getWord() {
		return "0";
	}

	@RequestMapping("/test")
	public String test() {
		return "0";
	}
}