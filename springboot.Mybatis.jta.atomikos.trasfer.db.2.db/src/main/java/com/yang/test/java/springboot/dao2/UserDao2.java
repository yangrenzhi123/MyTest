package com.yang.test.java.springboot.dao2;

import java.util.List;

import entity.GarbagebagPull;
import entity.InspectRecord;
import entity.RecycleLoseweight;
import entity.RecyleRecord;
import entity.ScoreRecord;

public interface UserDao2 {

	void insertBatch(List<ScoreRecord> l);
	void updateBatch(List<ScoreRecord> l);
	void insertBatchRecyleRecord(List<RecyleRecord> l);
	void insertBatchRecycleLoseweight(List<RecycleLoseweight> l);
	void insertBatchGarbagebagPull(List<GarbagebagPull> l);
	void insertBatchInspectRecord(List<InspectRecord> l);
}