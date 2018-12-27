package com.yang.test.java.spring.session;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class Config {

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceConnectionFactory r = new LettuceConnectionFactory();
		r.setHostName("172.28.51.33");
//		r.setPort(6379);
//		r.afterPropertiesSet();
//		r.setShareNativeConnection(true);
//		r.setTimeout(10000);
//		r.setShutdownTimeout(10000);
		return r;
	}
}