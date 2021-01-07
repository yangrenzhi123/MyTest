package com.yang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yang.dao.OrderMapper;
import com.yang.dao.UserMapper;

@Component
public class UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	OrderMapper orderMapper;

	public void selectById() {
		userMapper.selectByid();
		orderMapper.selectByid();
	}
}