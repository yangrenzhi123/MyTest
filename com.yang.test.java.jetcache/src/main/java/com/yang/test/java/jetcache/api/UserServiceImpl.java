package com.yang.test.java.jetcache.api;

import org.springframework.stereotype.Service;

import com.lyzh.msa.framework.common.dto.fl.TenantGroupDTO;

@Service
public class UserServiceImpl implements UserService {

	public String getUserById(String userId) {
		System.out.println("进入方法 getUserById");
		return userId;
	}

	public String getUserById(String userId, String userId2) {
		System.out.println("进入方法 getUserById");
		return userId;
	}

	public TenantGroupDTO findRegionDTOOne() {
		return null;
	}
}