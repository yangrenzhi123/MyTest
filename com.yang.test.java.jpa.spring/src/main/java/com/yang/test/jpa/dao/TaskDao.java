package com.yang.test.jpa.dao;

import java.util.List;

import com.yang.test.jpa.spring.entity.T;

public interface TaskDao {

	List<T> list();
	List<T> list2();
}