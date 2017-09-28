package com.yang.test.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class SpidGetter {

	@PersistenceContext
	protected EntityManager em;
	
	public Short getSpid(){
		Query q = em.createNativeQuery("select @@spid");
		Short spid = (Short)q.getSingleResult();
		return spid;
	}
}