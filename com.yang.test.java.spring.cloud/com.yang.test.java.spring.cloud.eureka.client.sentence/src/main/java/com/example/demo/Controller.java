package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/")
	public String getWord() throws InterruptedException {
		Thread.sleep(20000L);
		
		return "1";
	}
}