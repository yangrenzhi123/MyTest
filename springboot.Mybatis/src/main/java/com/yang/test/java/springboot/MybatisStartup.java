package com.yang.test.java.springboot;

import java.io.IOException;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.yang.test.java.springboot.mybatis.intercept.MybatisSqlInterceptor;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class MybatisStartup {

	@Bean
	public MybatisSqlInterceptor mybatisSqlInterceptor() {
		return new MybatisSqlInterceptor();
	}

	@Bean
	public SqlSessionFactoryBean createSqlSessionFactory(MybatisSqlInterceptor mybatisSqlInterceptor) throws IOException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.10.22:3306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
		dataSource.setUsername("lyzh_reader");
		dataSource.setPassword("lyzh_reader@123456");
        
		
		// 实例SessionFactory
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 配置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{mybatisSqlInterceptor});

		// 加载MyBatis配置文件
		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mapper/**/*.xml"));
		return sqlSessionFactoryBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(MybatisStartup.class, args);
	}
}