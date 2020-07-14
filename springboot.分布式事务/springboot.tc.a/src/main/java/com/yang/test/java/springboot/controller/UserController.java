package com.yang.test.java.springboot.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.rpc.Tcb;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	@Autowired
	Tcb tcb;
	
	@GetMapping("/")
	@ResponseBody
    @LcnTransaction //分布式事务注解
    //@Transactional //本地事务注解
	public void index() throws ClientProtocolException, IOException {
		userDao.insert();
		
		tcb.index();
		
		throw new RuntimeException();
	}
}