package com.yang.test.java.jetcache.api;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.yang.test.java.jetcache.RegionDTO;

public interface UserService {

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
	@Cached(name = "UserService.getUserById", cacheType = CacheType.BOTH, localExpire = 10, expire = 2592000)
	String getUserById(String userId);

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
	@Cached(name = "UserService.getUserById", key = "#userId+#userId2", cacheType = CacheType.BOTH, localExpire = 10, expire = 2592000)
	String getUserById(String userId, String userId2);

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
    @Cached(name = "h_region_xn_map:", key = "'f8d8957c-73a0-48c2-a95f-2b81d84a6a7f'",cacheType=CacheType.REMOTE)
	RegionDTO findRegionDTOOne();
}