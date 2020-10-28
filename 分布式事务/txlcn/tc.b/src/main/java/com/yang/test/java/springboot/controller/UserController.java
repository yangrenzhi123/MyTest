package com.yang.test.java.springboot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yang.test.java.springboot.dao.UserDao;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	@GetMapping("/")
	@ResponseBody
    @LcnTransaction //分布式事务注解
    //@Transactional //本地事务注解
	public void index() {
		userDao.insert();
	}
}