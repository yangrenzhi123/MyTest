package com.yang.test.servlet.springmvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.servlet.springmvc.kaoqin.KaoQinDto;
import com.yang.test.servlet.springmvc.kaoqin.KaoQinReponse;

@Test
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

	@Test
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