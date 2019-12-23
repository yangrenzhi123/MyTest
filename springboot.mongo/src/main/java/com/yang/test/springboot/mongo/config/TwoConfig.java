package com.yang.test.springboot.mongo.config;

import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class TwoConfig {

	@Bean(name = "two_MongoTemplate")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory(mongoProperties()));
	}

	public MongoDbFactory mongoDbFactory(MongoProperties mongoProperties) throws Exception {
		return new SimpleMongoDbFactory(new MongoClientURI(mongoProperties.getUri()));
	}

	@ConfigurationProperties(prefix = "spring.data.mongodb.two")
	@Bean(name = "twoMongoProperties")
	public MongoProperties mongoProperties() {
		return new MongoProperties();
	}
}