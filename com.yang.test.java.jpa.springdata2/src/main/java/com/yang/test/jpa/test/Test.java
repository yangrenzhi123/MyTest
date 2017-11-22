package com.yang.test.jpa.test;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yang.test.jpa.domain.T;
import com.yang.test.jpa.springdata2.TDao;

public class Test extends BaseJunit4Test {

	@Autowired
	TDao tDao;

	@org.junit.Test
	@Transactional
	@Rollback(false)
	public void test() {
		Random r = new Random();
		
		
		T t = tDao.findById(15).get();
		t.setValue(r.nextInt(1000));
		tDao.save(t);
		System.out.println();
	}
}