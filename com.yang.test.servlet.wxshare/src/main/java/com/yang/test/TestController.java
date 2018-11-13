package com.yang.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String iframeTest2(Model model) {
		model.addAttribute("nonceStr", "27ff9be5-4d0f-400c-8a10-aa15ec6eb4ba");
		model.addAttribute("timestamp", "1542110890");
		
		
		
		return "/iframeTest2";
	}
}