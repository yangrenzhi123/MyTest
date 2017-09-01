package com.yang.test.servlet.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String lotteryIndex(Model model) {
		System.out.println(1);
		return "/test";
	}
}