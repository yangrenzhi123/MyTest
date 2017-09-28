package com.yang.test.jpa.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.dao.TaskDao;

public class Test extends BaseJunit4Test {

	@Autowired
	TaskDao taskDao;

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void test() {
		System.out.println(1);
	}

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		TaskDao b = (TaskDao) ac.getBean("taskDao");
		b.delete(1);
	}
}