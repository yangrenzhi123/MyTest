package com.yang.test.java.orm.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMybatis2 {
	
	public static void main(String[] args) throws IOException {
		Properties pp = new Properties();
		pp.setProperty("username", "root");
		
		
		String resource = "com/yang/test/java/orm/mybatis/mybatis-config2.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is, "e2", pp);
		
		sqlSessionFactory.getConfiguration().getMappedStatementNames();
		
		SqlSession session = sqlSessionFactory.openSession();
		
        T user = session.selectOne("selectBlog", 1);
        System.out.println(user.toString());
	}
}