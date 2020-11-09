package com.yang.test.java.jetcache.api;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.lyzh.msa.framework.common.dto.fl.TenantGroupDTO;

public interface UserService {

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
	@Cached(name = "h_equip_dispenser:", key = "#userId", cacheType = CacheType.LOCAL, expire = 60) // 60秒
	String getUserById(String userId);

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
	@Cached(name = "UserService.getUserById", key = "#userId+#userId2", cacheType = CacheType.BOTH, localExpire = 10, expire = 2592000)
	String getUserById(String userId, String userId2);

	@CachePenetrationProtect // 表示在多线程环境中同步加载数据
    @Cached(name = "h_tenant_group:", key = "'LYZH1b618h2dp001'",cacheType=CacheType.REMOTE)
	TenantGroupDTO findRegionDTOOne();
}