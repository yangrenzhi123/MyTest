package com.yang.test.servlet.springmvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public void test(HttpServletResponse response) {
		throw new RuntimeException("测试异常");
	}
}