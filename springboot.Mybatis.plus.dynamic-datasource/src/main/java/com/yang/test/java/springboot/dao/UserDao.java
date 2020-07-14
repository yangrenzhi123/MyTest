package com.yang.test.java.springboot.dao;

import com.baomidou.dynamic.datasource.annotation.DS;

public interface UserDao {

	@DS("slave_1")
	long selectByPrimaryKey();
}