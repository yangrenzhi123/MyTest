package com.yang.test.java.springboot;

import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class MybatisStartup {

	@Bean
	public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.10.10:3306/t?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
        
		
		// 实例SessionFactory
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 配置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);

		// 加载MyBatis配置文件
		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mapper/**/*.xml"));
		return sqlSessionFactoryBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(MybatisStartup.class, args);
	}
}