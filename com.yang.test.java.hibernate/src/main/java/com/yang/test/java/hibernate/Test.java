package com.yang.test.java.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.metamodel.MetadataSources;

public class Test {

	public static void main(String[] args) {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(t);
		t.begin();
		t.commit();

		Transaction t2 = session.beginTransaction();
		System.out.println(t2);
		t2.commit();
		session.close();
	}
}