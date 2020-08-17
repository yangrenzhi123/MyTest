package com.yang.test.java.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

	void insert();
}