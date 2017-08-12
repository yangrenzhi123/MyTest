package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.yang.test.jpa.domain.Task;

@SuppressWarnings("unchecked")
@Repository("taskDao")
public class TaskDaoImpl implements TaskDao{

	@PersistenceContext
	private EntityManager em;

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		TaskDao b = (TaskDao)ac.getBean("taskDao");
		List<Task> l = b.list();
	    System.out.println(l);
	}

	public List<Task> list() {

		Query q = em
				.createQuery("SELECT new Task(one.deadline, one.createdOn, one.taskTitle,one.description, one.handleBy) FROM Task one WHERE one.handleBy = :handleBy");
		q.setParameter("handleBy", 11);
		List<Task> l = q.getResultList();

		return l;
	}

}
