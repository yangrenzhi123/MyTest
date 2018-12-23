package com.yang.test.java.jetcache.api;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public String getUserById(String userId) {
		return userId;
	}
}