package com.yang.test.java.springboot.dao;

import java.util.List;

import com.yang.test.java.springboot.entity.ScoreRecord;

public interface UserDao {

	List<ScoreRecord> page(Long start);
	
	void insertBatch(List<ScoreRecord> l);
}