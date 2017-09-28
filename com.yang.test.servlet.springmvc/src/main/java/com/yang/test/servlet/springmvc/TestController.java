package com.yang.test.servlet.springmvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/test")
@Controller
public class TestController {

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(HttpServletResponse response) {
		return "/test";
	}

	@RequestMapping
	public String test2(HttpServletResponse response) {
		return "/test";
	}
}