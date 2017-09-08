package com.yang.test.servlet.springmvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String lotteryIndex(HttpServletResponse response) {
		response.setHeader("Connection", "close");
		//System.out.println(1);
		return "/test";
	}
}