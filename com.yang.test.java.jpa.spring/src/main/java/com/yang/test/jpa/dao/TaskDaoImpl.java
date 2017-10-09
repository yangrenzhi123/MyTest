package com.yang.test.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {

	@PersistenceContext(unitName = "forC3po")
	private EntityManager em;

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao");
		int i= 0;
		while (i++ < 100) {

			new Thread(new Runnable() {

				public void run() {
					b.test();
				}
			}).start();
		}
		
		System.out.println("end");
	}

	public void test() {
		Query q = em.createNativeQuery("select @@spid");
		Short spid = (Short) q.getSingleResult();
		System.out.println(spid);
	}
}