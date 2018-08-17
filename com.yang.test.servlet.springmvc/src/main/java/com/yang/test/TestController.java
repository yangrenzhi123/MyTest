package com.yang.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

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
	

	@ResponseBody
	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public void t(HttpServletResponse res) throws UnsupportedEncodingException, IOException {
		
		
		res.setContentType("application/json;charset=GBK");
		res.getOutputStream().write("{a:'中'}".getBytes("UTF-8"));
	}
}