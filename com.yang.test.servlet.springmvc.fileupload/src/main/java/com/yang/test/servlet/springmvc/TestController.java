package com.yang.test.servlet.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/test")
@Controller
public class TestController {

	@Autowired
	private MathService mathService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String lotteryIndex(Model model) {
		return "/test";
	}
}