package com.yang.test.jpa.test;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yang.test.jpa.springdata2.TDao;

public class Test extends BaseJunit4Test {

	@Autowired
	TDao tDao;

	@org.junit.Test
	@Transactional
	@Rollback(false)
	public void test() {
		tDao.deleteById(15);
	}
}