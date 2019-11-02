package com.yang.test.java.springboot.dao2;

import java.util.List;

import entity.ScoreRecord;

public interface UserDao2 {

	void insertBatch(List<ScoreRecord> l);
}