package com.yang.test.java.springboot.dao1;

import java.util.List;

import entity.GarbagebagPull;
import entity.InspectRecord;
import entity.RecycleLoseweight;
import entity.RecyleRecord;
import entity.ScoreRecord;

public interface UserDao1 {

	List<ScoreRecord> page(Long start);
	List<RecyleRecord> limitRecyleRecord(Long start);
	List<RecycleLoseweight> limitRecycleLoseweight(Long start);
	List<GarbagebagPull> limitGarbagebagPull(Long start);
	List<InspectRecord> limitInspectRecord(Long start);
}