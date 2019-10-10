package com.yang.test.java.jetcache.api;

import org.springframework.stereotype.Service;

import com.yang.test.java.jetcache.RegionDTO;

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

    public RegionDTO findRegionDTOOne(){
        return null;
    }
}