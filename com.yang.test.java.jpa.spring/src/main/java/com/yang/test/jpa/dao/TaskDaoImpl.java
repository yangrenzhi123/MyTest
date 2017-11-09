package com.yang.test.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.spring.entity.Super;
import com.yang.test.jpa.spring.entity.T;

@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {

	@PersistenceContext(unitName = "forC3po")
	private EntityManager em;
	@PersistenceContext(unitName = "default")
	private EntityManager df;

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao");
		b.test();
	}

	@Transactional(value = "transactionManager")
	public void test() {
		T t = new T();
		t.setValue(2);
		Super s = (Super) t;

		df.persist(s);
	}
}