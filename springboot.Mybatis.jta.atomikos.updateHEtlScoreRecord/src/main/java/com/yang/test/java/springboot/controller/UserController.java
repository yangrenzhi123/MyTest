package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao1.UserDao1;
import com.yang.test.java.springboot.dao2.UserDao2;
import com.yang.test.java.springboot.entity.HEtlScoreRecord;
import com.yang.test.java.springboot.entity.HScoreRecord;

@Controller
public class UserController {

	@Resource
	private UserDao1 userDao1;
	@Resource
	private UserDao2 userDao2;

	@Transactional
	@GetMapping("/")
	@ResponseBody
	public void hello2() {
		List<HEtlScoreRecord> l = userDao1.page();
		
		if(l != null && l.size() > 0) {
			List<HScoreRecord> ll = userDao2.sflszhInScorerecordid(l);
		}
	}
}