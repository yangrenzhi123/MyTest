package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.spring.entity.T;

@Repository("taskDao3")
public class TaskDaoImpl3 implements TaskDao {

	@PersistenceContext
	private EntityManager em;

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao3");
		while (true) {
			new Thread(new Runnable() {
				
				public void run() {
					b.list();
				}
			}).start();
			
			Thread.sleep(100);
		}
	}

	@Transactional
	public List<T> list() {
		T t = new T();
		T t2 = new T();
		T t3 = new T();
		T t4 = new T();
		T t5 = new T();
		T t6 = new T();
		T t7 = new T();
		T t8 = new T();
		T t9 = new T();
		T t10 = new T();
		em.persist(t);
		em.persist(t2);
		em.persist(t3);
		em.persist(t4);
		em.persist(t5);
		em.persist(t6);
		em.persist(t7);
		em.persist(t8);
		em.persist(t9);
		em.persist(t10);
		t.getId();
		t2.getId();
		t3.getId();
		t4.getId();
		t5.getId();
		t6.getId();
		t7.getId();
		t8.getId();
		t9.getId();
		t10.getId();

		return null;
	}

	public List<T> list2() {
		return null;
	}
}