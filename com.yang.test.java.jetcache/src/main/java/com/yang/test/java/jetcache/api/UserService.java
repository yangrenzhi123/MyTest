package com.yang.test.java.jetcache.api;

import com.alicp.jetcache.anno.Cached;

public interface UserService {

	@Cached(name = "UserService.getUserById", expire = 3600)
	String getUserById(String userId);
}