package com.yang.test.java.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yang.test.java.springboot.intercept.SqlStatementInterceptor;

@Configuration
public class MybatisConfig {

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {

		return new ConfigurationCustomizer() {
			public void customize(org.apache.ibatis.session.Configuration configuration) {
				configuration.addInterceptor(new SqlStatementInterceptor());
			}
		};
	}
}