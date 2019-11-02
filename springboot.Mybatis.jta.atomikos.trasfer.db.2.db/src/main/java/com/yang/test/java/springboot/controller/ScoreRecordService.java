package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.java.springboot.dao2.UserDao2;

import entity.ScoreRecord;

@Service
public class ScoreRecordService {

	@Resource
	private UserDao2 userDao2;

	@Transactional
	public void insertBatch(List<ScoreRecord> l) {
		userDao2.insertBatch(l);
	}
}