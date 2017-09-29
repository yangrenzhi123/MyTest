package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.yang.test.jpa.spring.entity.T;

@SuppressWarnings("unchecked")
@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {

	@PersistenceContext
	private EntityManager em;

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		TaskDao b = (TaskDao) ac.getBean("taskDao");
		b.getPrint();
	}

	public static void main2(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		TaskDao b = (TaskDao) ac.getBean("taskDao");
		List<T> l = b.list();
		System.out.println(l);
	}

	public List<T> list() {
		Query q = em.createQuery("from T WHERE value = :value");
		q.setParameter("value", 1);
		List<T> l = q.getResultList();

		return l;
	}

	public List<T> list2() {
		return null;
	}

	public void getPrint() {
		Query q = em.createNativeQuery("exec TP");
		List<Object[]> l = (List<Object[]>) q.getResultList();
		System.out.println(l);
	}
}