package com.yang.test.java.springboot.dao1;

import java.util.List;

import entity.HEtlScoreRecord;
import entity.HScoreRecord;

public interface UserDao1 {

	List<HEtlScoreRecord> page(long start);

	void updateBatch(List<HScoreRecord> l);
}