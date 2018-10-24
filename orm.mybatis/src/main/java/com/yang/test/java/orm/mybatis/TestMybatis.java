package com.yang.test.java.orm.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMybatis {

	public static void main(String[] args) throws IOException {
		String resource = "com/yang/test/java/orm/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		
		String id = UUID.randomUUID().toString();
		session.insert("add", id);
		session.commit();
		

		T t = session.selectOne("selectBlog", id);
		System.out.println(t.toString());
	}
}