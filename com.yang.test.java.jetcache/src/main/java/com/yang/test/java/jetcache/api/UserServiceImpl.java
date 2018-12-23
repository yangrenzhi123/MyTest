package com.yang.test.java.jetcache.api;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public String getUserById(String userId) {
		System.out.println("进入方法 getUserById");
		return userId;
	}
}