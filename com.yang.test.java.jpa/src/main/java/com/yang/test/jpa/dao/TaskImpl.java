package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.yang.test.jpa.domain.Task;

@SuppressWarnings("unchecked")
public class TaskImpl {

	public static void main(String[] args) {
		new TaskImpl().list();
	}
	
	public List<Task> list() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistenceUnit");
		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("SELECT new Task(one.deadline, one.createdOn, one.taskTitle,one.description, one.handleBy) FROM Task one WHERE one.handleBy = :handleBy");
		q.setParameter("handleBy", 11);
		List<Task> l = q.getResultList();

		emf.close();
		return l;
	}

}
