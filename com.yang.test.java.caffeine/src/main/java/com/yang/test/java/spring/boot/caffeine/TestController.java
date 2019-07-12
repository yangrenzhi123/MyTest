package com.yang.test.java.spring.boot.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@Autowired
	TestService testService;
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		TestDto t = testService.findById(1);
		if(t == null) {
			return null;
		}
		
		return t.getName();
	}
}