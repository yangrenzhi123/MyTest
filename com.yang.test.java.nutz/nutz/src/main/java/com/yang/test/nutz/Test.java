package com.yang.test.nutz;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.Daos;

public class Test {

	public static void main(String[] args) {
		test2();
	}
	
	public void test1() {
		// 创建一个数据源
		SimpleDataSource dataSource = new SimpleDataSource();
		dataSource.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		dataSource.setUsername("orcl");
		dataSource.setPassword("orcl");

		// 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
		Dao dao = new NutDao(dataSource);

		// 创建表
		dao.create(Person.class, false); // false的含义是,如果表已经存在,就不要删除重建了.

		Person p = new Person();
		p.setName("ABC2");
		p.setAge(20);
		dao.insert(p);
		System.out.println(p.getId());
	}
	
	public static void test2() {
		// 创建一个数据源
		SimpleDataSource dataSource = new SimpleDataSource();
		dataSource.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		dataSource.setUsername("orcl");
		dataSource.setPassword("orcl");

		// 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
		Dao dao = new NutDao(dataSource);
		
		Daos.migration(dao, Person.class, true, false, false); // 新增字段true,删除字段false,检查索引false
	}
}