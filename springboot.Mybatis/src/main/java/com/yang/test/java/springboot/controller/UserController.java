package com.yang.test.java.springboot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.User;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserDao userDao;

	@GetMapping("/showUser")
	@ResponseBody
	public Mono<User> hello() {
		User user = userDao.selectByPrimaryKey(1);
		return Mono.just(user);
	}
}