package com.yang.test.jpa.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yang.test.jpa.dao.TaskDao;
import com.yang.test.jpa.dao.TreeDao;
import com.yang.test.jpa.domain.Task;
import com.yang.test.jpa.domain.Tree;
import com.yang.test.jpa.service.TreeService;

public class Test {

	public static void main(String[] args) {
		test5();
	}

	public static void test5(){
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		
		TreeService b = (TreeService) ac.getBean("treeServiceImpl");
		b.test();
	}
	
	public static void test4(){
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		
		TreeDao b = (TreeDao) ac.getBean("treeDao");
		b.deleteByParentId(1000);
	}
	
	public static void test3(){
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");

		TreeService b = (TreeService) ac.getBean("treeServiceImpl");
		List<Long> l = b.getSub(1000L);
	}
	
	public static void test2() {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");

		Tree tree0 = new Tree(100011L, 10001L);
		Tree tree2 = new Tree(100012L, 10001L);
		Tree tree3 = new Tree(100013L, 10001L);
		Tree tree4 = new Tree(100014L, 10001L);
		Tree tree5 = new Tree(100015L, 10001L);

		TreeDao b = (TreeDao) ac.getBean("treeDao");
		b.save(tree0);
		b.save(tree2);
		b.save(tree3);
		b.save(tree4);
		b.save(tree5);
	}

	public static void test() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"classpath:root-context.xml");
		TaskDao b = (TaskDao) ac.getBean("taskDao");
		List<Task> l = b.findTop10();
		System.out.println(l);
	}

}