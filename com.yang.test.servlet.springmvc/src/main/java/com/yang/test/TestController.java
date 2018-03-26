package com.yang.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yang.test.jpa.dao.TaskDao;

@Controller
public class TestController {

	@Autowired
	ITestService testService;
	

	@Autowired
	TaskDao taskDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String iframeTest2() {
		return "/iframeTest2";
	}
}