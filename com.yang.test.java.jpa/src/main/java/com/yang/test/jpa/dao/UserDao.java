package com.yang.test.jpa.dao;

import java.util.List;

import com.yang.test.jpa.domain.User;

public interface UserDao {
	List<User> list();
}