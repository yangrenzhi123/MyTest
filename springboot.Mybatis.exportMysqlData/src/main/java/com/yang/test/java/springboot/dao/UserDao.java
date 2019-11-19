package com.yang.test.java.springboot.dao;

import java.util.List;

import com.yang.test.java.springboot.entity.RecycleRecord;
import com.yang.test.java.springboot.entity.ScoreRecord;

public interface UserDao {

	List<ScoreRecord> page(Long start);
	List<RecycleRecord> limitRecycleRecord(Long start);
}