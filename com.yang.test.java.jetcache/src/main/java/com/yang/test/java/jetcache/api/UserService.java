package com.yang.test.java.jetcache.api;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

public interface UserService {

	@Cached(name = "UserService.getUserById", cacheType = CacheType.REMOTE, expire = 3600)
	String getUserById(String userId);
}