package com.yang.test.java.springboot.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.java.springboot.entity.RecycleRecord;

@Service
public class ExchangeRecordService {

	@Resource
	private UserDao userDao;

	@Transactional
	public void exec(List<RecycleRecord> l) {
		long b = System.currentTimeMillis();
		userDao.insertBatch(l);
		long bb = System.currentTimeMillis() - b;

		long c = System.currentTimeMillis();
		userDao.updateBatch(l);
		long cc = System.currentTimeMillis() - c;

		System.out.println("插入耗时：" + bb + "，更新耗时：" + cc);
		System.out.println("");
	}
}