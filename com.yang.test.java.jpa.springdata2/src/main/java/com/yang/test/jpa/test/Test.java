package com.yang.test.jpa.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yang.test.jpa.domain.T;
import com.yang.test.jpa.springdata2.TDao;

public class Test extends BaseJunit4Test {

	@Autowired
	TDao tDao;

	@org.junit.Test
	//@Transactional
	@Rollback(true)
	public void test() {
		List<T> l = tDao.findAll();
		System.out.println(l);
	}
}