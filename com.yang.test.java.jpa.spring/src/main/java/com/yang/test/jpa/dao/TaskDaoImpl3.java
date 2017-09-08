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

@Repository("taskDao3")
public class TaskDaoImpl3 implements TaskDao {

	@PersistenceContext
	private EntityManager em;

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		TaskDao b = (TaskDao) ac.getBean("taskDao3");
		List<T> l = b.list();
		System.out.println(l);
	}

	@Transactional
	public List<T> list() {
		Query q = em.createQuery("update T set value = 2");
		q.executeUpdate();

		return null;
	}

	public List<T> list2() {
		return null;
	}
}