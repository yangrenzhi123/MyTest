package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.spring.entity.T;

@Repository("taskDao2")
public class TaskDaoImpl2 implements TaskDao {

	@PersistenceContext
	private EntityManager em;

	@PersistenceContext
	private EntityManager em2;

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao2");

		new Thread(new Runnable() {

			public void run() {
				b.list();
				b.list2();
			}
		}).start();

//		new Thread(new Runnable() {
//
//			public void run() {
//				b.list();
//			}
//		}).start();
	}


	@Transactional(value = "transactionManager")
	public List<T> list() {
		
		
		Query qspid = em2.createNativeQuery("select @@spid");
		Short spid = (Short) qspid.getSingleResult();
		System.out.println(spid);
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<T> list2() {
		Query qspid2 = em.createNativeQuery("select @@spid");
		Short spid2 = (Short) qspid2.getSingleResult();
		System.out.println(spid2);
		return null;
	}


	public void getPrint() {
		// TODO Auto-generated method stub
		
	}
}