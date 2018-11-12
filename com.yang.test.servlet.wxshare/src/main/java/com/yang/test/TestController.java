package com.yang.test;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String iframeTest2(Model model) {
		model.addAttribute("nonceStr", UUID.randomUUID().toString());
		model.addAttribute("timestamp", Long.toString(System.currentTimeMillis() / 1000));
		
		
		
		return "/iframeTest2";
	}
}