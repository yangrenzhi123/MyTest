package com.yang.test.java.springboot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;

import io.seata.spring.annotation.GlobalTransactional;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	@GetMapping("/")
	@ResponseBody
    @GlobalTransactional(rollbackFor = Exception.class)
	public void index() {
		userDao.insert();
	}
}