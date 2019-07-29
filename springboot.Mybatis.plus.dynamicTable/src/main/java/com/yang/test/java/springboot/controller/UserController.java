package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.Tt;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	@GetMapping("/")
	@ResponseBody
	public int index() {
		List<Tt> userList = userDao.selectList(null);
		return userList.size();
	}
}