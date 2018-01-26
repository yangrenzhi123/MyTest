package com.yang.test.servlet.springmvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.servlet.springmvc.kaoqin.KaoQinDto;
import com.yang.test.servlet.springmvc.kaoqin.KaoQinReponse;

@Controller
public class TestController {

	@RequestMapping(value = "/test/json", method = RequestMethod.POST)
	@ResponseBody
	public KaoQinReponse accept(@RequestBody KaoQinDto data) {
		KaoQinReponse result = new KaoQinReponse();
		result.setStatus(100);
		result.setStatusMessage("成功");
		return result;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(HttpServletResponse response) {
		return "{\"aa\":\"bb\",\"a\":{\"b\":[\"5\", \"6\", \"3\", \"4\"]}}";
	}

	@RequestMapping(value = "/iframeTest", method = RequestMethod.GET)
	public String iframeTest(HttpServletResponse response) {

		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		Cookie cookie = new Cookie("t", "123");
		cookie.setDomain("218.244.151.137");
		cookie.setMaxAge(31536000);
		cookie.setPath("/");
		httpServletResponse.addCookie(cookie);
		
		
		return "/iframeTest";
	}

	@RequestMapping(value = "/iframeTest2", method = RequestMethod.GET)
	public String iframeTest2() {
		return "/iframeTest2";
	}
}