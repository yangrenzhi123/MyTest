package com.yang.test.java.orm.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class TestMybatis3 {
	
	public static void main(String[] args) throws IOException {
		DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.197.81:3307/test?characterEncoding=utf-8", "root", "123456");
		Environment environment = new Environment("test", new JdbcTransactionFactory(), dataSource);
		
		
		Configuration config = new Configuration();
		config.setEnvironment(environment);
		//config.addMapper(IUser.class);
		config.addMappers("com.yang.test.java.orm.mybatis");

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
		
		SqlSession session = sqlSessionFactory.openSession();
		
        T user = session.selectOne("getUserByID", 1);
        System.out.println(user.toString());
	}
}