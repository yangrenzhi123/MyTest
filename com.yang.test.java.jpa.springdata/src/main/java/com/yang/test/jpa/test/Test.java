package com.yang.test.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.jpa.domain.Specification;

import com.yang.test.jpa.dao.TaskDao;
import com.yang.test.jpa.domain.Tab;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");

		TaskDao b = (TaskDao) ac.getBean("taskDao");
		List<Tab> t = (List<Tab>) b.findAll(new Specification<Tab>() {

			public Predicate toPredicate(Root<Tab> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("id").as(Integer.class), 1));

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		});
		System.out.println(t);
	}
}