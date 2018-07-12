package com.yang.test.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
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
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        List<Selection<?>> selections = new ArrayList<Selection<?>>();
		        selections.add(root.get("id").alias("id"));
		        
		        query.multiselect(selections);
		        
		        query.groupBy(root.get("id"));
				
				return query.getGroupRestriction();
			}
		});
	}
}