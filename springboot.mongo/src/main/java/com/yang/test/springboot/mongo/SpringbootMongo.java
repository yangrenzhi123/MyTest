package com.yang.test.springboot.mongo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.springboot.mongo.entity.MongoEntity;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication
public class SpringbootMongo {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringbootMongo.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
	MongoTemplate mongoTemplate;

	@GetMapping("/")
	public MongoEntity index() {
		Query query = new Query(Criteria.where("_id").is("5c5040cb-d239-4710-aa51-1b0023cdecd4"));
		MongoEntity e = mongoTemplate.findOne(query, MongoEntity.class, "deviceOriginalData");
		return e;
	}
}