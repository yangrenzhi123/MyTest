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
		
		Query qspid = em.createNativeQuery("select @@spid");
		Short spid = (Short)qspid.getSingleResult();
		System.out.println(spid);
		
		Query q = em.createQuery("FROM User");
		List<User> l = q.getResultList();
		
		for(User item : l){
			item.setQq("1");
			em.refresh(item);
		}
		emf.close();
		return l ;
	}

	public User save(User accountInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}