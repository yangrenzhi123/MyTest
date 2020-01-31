package com.yang.test.java.springboot.dao1;

import java.util.List;

import entity.DispenserReplenish;
import entity.GarbagebagPull;
import entity.InspectRecord;
import entity.RecycleLoseweight;
import entity.RecyleRecord;
import entity.ReplenishContent;
import entity.ScoreRecord;

public interface UserDao1 {

	List<ScoreRecord> page(Long start);
	void deleteBatch(List<ScoreRecord> l);
	List<RecyleRecord> limitRecyleRecord(Long start);
	void deleteRecyleRecordBatch(List<RecyleRecord> l);
	List<RecycleLoseweight> limitRecycleLoseweight(Long start);
	void deleteRecycleLoseweightBatch(List<RecycleLoseweight> l);
	List<GarbagebagPull> limitGarbagebagPull(Long start);
	List<InspectRecord> limitInspectRecord(Long start);
	List<DispenserReplenish> limitDispenserReplenish(Long start);
	List<ReplenishContent> limitReplenishContent(Long start);
}