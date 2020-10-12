package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.java.springboot.dao1.UserDao1;

import entity.HScoreRecord;

@Service
public class MyService {

	@Resource
	private UserDao1 userDao1;

	@Transactional
	public void updateBatch(List<HScoreRecord> ll) {
		userDao1.updateBatch(ll);
	}
}