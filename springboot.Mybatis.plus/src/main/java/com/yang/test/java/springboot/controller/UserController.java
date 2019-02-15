package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.User;

import reactor.core.publisher.Mono;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	@GetMapping("/user/webflow/showUser")
	@ResponseBody
	public Mono<User> hello() {
		User user = userDao.selectByPrimaryKey(1);
		return Mono.just(user);
	}

	@GetMapping("/user/mvc/showUser")
	@ResponseBody
	public User hello2() {
		User user = userDao.selectByPrimaryKey(1);
		return user;
	}

	@GetMapping("/")
	@ResponseBody
	public int index() {
		List<User> userList = userDao.selectList(null);
		return userList.size();
	}
}