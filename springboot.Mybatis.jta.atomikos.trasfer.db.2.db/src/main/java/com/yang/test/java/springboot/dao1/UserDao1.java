package com.yang.test.java.springboot.dao1;

import java.util.List;

import entity.ScoreRecord;

public interface UserDao1 {

	List<ScoreRecord> page(Long start);
}