package com.yang.test.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistenceUnit");
		final EntityManager em = emf.createEntityManager();

		new Thread(new Runnable() {

			public void run() {
				Query qspid = em.createNativeQuery("select @@spid");
				Short spid = (Short) qspid.getSingleResult();
				System.out.println(spid);
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				Query qspid = em.createNativeQuery("select @@spid");
				Short spid = (Short) qspid.getSingleResult();
				System.out.println(spid);
			}
		}).start();
	}
}