package com.yang.test.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import com.yang.test.jpa.domain.T;
import com.yang.test.jpa.springdata2.TDao;

@SuppressWarnings("serial")
public class Test extends BaseJunit4Test {

	@Autowired
	TDao tDao;

	@org.junit.Test
	@Transactional
	@Rollback(false)
	public void test() {
		tDao.findAll(new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("id"), 18));
				Predicate[] p = new Predicate[list.size()];
				Predicate p2 = cb.or(new Predicate[]{cb.equal(root.get("id"), 16), cb.equal(root.get("id"), 17)});
				list.add(p2);
				return cb.and(list.toArray(p));
			}
		});
	}
}