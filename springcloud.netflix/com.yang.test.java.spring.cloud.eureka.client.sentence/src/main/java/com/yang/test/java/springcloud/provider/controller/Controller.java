package com.yang.test.java.springcloud.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	public static int i = 0;

	@RequestMapping("/")
	public String getWord() throws InterruptedException {
		System.out.println("第" + (++i) + "次进入");

		if(i % 2 == 0) Thread.sleep(3000);

		return "0";
	}
}