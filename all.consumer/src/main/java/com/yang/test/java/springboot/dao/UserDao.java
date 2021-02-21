package com.yang.test.java.springboot.dao;

import com.yang.test.java.springboot.entity.User;

public interface UserDao {

	void insert();

	User selectByPrimaryKey(Integer id);
}