package com.yang.test.java.spring.cloud.zuul;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/user")
	@ResponseBody
	String home() {
		return "1";
	}
}