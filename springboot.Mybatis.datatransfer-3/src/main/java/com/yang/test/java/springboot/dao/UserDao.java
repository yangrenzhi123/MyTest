package com.yang.test.java.springboot.dao;

import java.util.List;

import com.yang.test.java.springboot.entity.RecycleRecord;

public interface UserDao {

	List<RecycleRecord> page();
	
	void insertBatch(List<RecycleRecord> l);
	void updateBatch(List<RecycleRecord> l);
}