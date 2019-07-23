package com.yang.test.java.springboot.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.User;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	@GetMapping("/")
	@ResponseBody
	public void index() {
		User u = new User();
		u.setId(1);
		u.setValue(UUID.randomUUID().toString());
		u.setVersion(1);
		
		
		System.out.println(userDao.updateById(u));
	}
}