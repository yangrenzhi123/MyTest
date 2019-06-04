package com.yang.test.java.jetcache.api;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

public interface UserService {

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
	@Cached(name = "UserService.getUserById", cacheType = CacheType.BOTH, localExpire = 10, expire = 2592000)
	String getUserById(String userId);

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
	@Cached(name = "UserService.getUserById", key = "#userId+#userId2", cacheType = CacheType.BOTH, localExpire = 10, expire = 2592000)
	String getUserById(String userId, String userId2);
}