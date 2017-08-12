package com.yang.test.jpa.dao;

import java.util.List;

import com.yang.test.jpa.domain.User;

public interface UserDao {
	User save(User accountInfo);

	List<User> list();
}