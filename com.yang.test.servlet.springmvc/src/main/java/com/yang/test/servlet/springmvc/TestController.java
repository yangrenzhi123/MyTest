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
	public String test(HttpServletResponse response) {
		return "{\"aa\":\"bb\",\"a\":{\"b\":[\"5\", \"6\", \"3\", \"4\"]}}";
	}

	@RequestMapping(value = "/iframeTest", method = RequestMethod.GET)
	public String iframeTest() {
		return "/iframeTest";
	}

	@RequestMapping(value = "/iframeTest2", method = RequestMethod.GET)
	public String iframeTest2() {
		return "/iframeTest2";
	}
}