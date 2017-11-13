package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		Query q = df.createNativeQuery("select * from T t");
		List l = q.getResultList();
		System.out.println(l);
	}
}