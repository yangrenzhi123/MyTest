package com.yang.test.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.yang.test.jpa.dao.SpidGetter;
import com.yang.test.jpa.dao.TaskDao;
import com.yang.test.jpa.domain.T;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
//		SpidGetter spidGetter = (SpidGetter) ac.getBean("spidGetter");
//		Short spid = spidGetter.getSpid();
//		System.out.println(spid);

		TaskDao b = (TaskDao) ac.getBean("taskDao");
		b.delete(1);
		

//		Sort sort = new Sort(Direction.DESC, "id");
//		Pageable pageable = new PageRequest(0, 1, sort);
//		
//		Page<T> t = (Page<T>) b.findAll(new Specification<T>() {
//
//			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				List<Predicate> list = new ArrayList<Predicate>();
//				list.add(cb.equal(root.get("id").as(Integer.class), 1));
//
//				Predicate[] p = new Predicate[list.size()];
//				return cb.and(list.toArray(p));
//			}
//		}, pageable);
	}
}