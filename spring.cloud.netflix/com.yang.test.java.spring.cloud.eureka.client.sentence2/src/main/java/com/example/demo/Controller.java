package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/")
	public String getWord() {
		return "1";
	}

	@RequestMapping("/test")
	public String test() {
		return "1";
	}
}