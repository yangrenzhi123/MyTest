package com.yang.test.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;
	
	@RequestMapping("/")
	@ResponseBody
	public String test() {
		
		userDao.insert();
		return "1";
	}
}