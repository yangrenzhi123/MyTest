package com.yang.test.java.springcloud.txmanager;

import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
//import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

//import com.alibaba.druid.pool.DruidDataSource;
import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagerServer
public class TxManagerStartup {

//	//@Bean
//	public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
//		DruidDataSource dataSource = new DruidDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://192.168.8.70:3306/tx-manager?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
//		dataSource.setUsername("root");
//		dataSource.setPassword("123456");
//        
//		
//		// 实例SessionFactory
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		// 配置数据源
//		sqlSessionFactoryBean.setDataSource(dataSource);
//
//		// 加载MyBatis配置文件
//		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//		sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mapper/**/*.xml"));
//		return sqlSessionFactoryBean;
//	}
	
	@Bean
	public EntityManagerFactory createEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(TxManagerStartup.class, args);
	}
}