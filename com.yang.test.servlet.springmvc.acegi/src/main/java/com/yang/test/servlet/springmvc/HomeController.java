package com.yang.test.servlet.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/")
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String lotteryIndex(Model model) {
		return "/test";
	}

}
