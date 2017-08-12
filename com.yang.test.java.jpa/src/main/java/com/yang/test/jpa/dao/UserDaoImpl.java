package com.yang.test.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.yang.test.jpa.domain.User;

@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {

	public static void main(String[] args) {
		new UserDaoImpl().list();
	}
	
	public List<User> list() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT new User(name, qq) FROM User");
		List<User> l = q.getResultList();
		
		emf.close();
		return l ;
	}

	public User save(User accountInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}