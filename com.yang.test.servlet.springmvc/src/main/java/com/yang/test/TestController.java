package com.yang.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@ResponseBody
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String iframeTest2(@RequestParam("accountCode") String cc) {
		return "123";
	}
}