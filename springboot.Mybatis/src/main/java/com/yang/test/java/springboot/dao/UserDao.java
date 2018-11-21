package com.yang.test.java.springboot.dao;

import com.yang.test.java.springboot.entity.User;

public interface UserDao {
	
	User selectByPrimaryKey(Integer id);
}