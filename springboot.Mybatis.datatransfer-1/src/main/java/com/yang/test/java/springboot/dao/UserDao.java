package com.yang.test.java.springboot.dao;

import java.util.List;

import com.yang.test.java.springboot.entity.RecyleRecord;
import com.yang.test.java.springboot.entity.ScoreRecord;

public interface UserDao {
	List<RecyleRecord> limitRecycleRecord(Long start);
	void insertBatchRecycleRecord(List<RecyleRecord> l, Integer month);

	List<ScoreRecord> page(Long start);
	void insertBatch(List<ScoreRecord> l);
	void cleanETL(List<ScoreRecord> l);
}