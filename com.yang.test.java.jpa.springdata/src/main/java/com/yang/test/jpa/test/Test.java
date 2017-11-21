package com.yang.test.jpa.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.dao.TaskDao;

public class Test extends BaseJunit4Test {

	@Autowired
	TaskDao taskDao;

	@org.junit.Test
	//@Transactional
	@Rollback(true)
	public void test() {
		
		new Thread(new Runnable() {
			
			public void run() {
				taskDao.findAll();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				taskDao.findAll();
			}
		}).start();
		
		System.out.println();
	}
}