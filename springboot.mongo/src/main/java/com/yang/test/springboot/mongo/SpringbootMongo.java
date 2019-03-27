package com.yang.test.springboot.mongo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	public void index() {
		MongoEntity m3 = new MongoEntity();
		m3.set_id(UUID.randomUUID().toString());
		m3.setData("3");
		m3.setStatus(1);
		m3.setReceiveTime(new Date());
		MongoEntity m2 = new MongoEntity();
		m2.set_id("3af8fa6c-1580-418e-a2c1-3e4c5b763e2a");
		m2.setData("2");
		m2.setStatus(1);
		m2.setReceiveTime(new Date());
		MongoEntity m1 = new MongoEntity();
		m1.set_id(UUID.randomUUID().toString());
		m1.setData("1");
		m1.setStatus(1);
		m1.setReceiveTime(new Date());

		List<MongoEntity> l = new ArrayList<MongoEntity>();
		l.add(m1);
		l.add(m2);
		l.add(m3);

		try {
			mongoTemplate.insert(l, "test");
		} catch (org.springframework.dao.DuplicateKeyException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/q")
	public MongoEntity q() {
		Query query = new Query(Criteria.where("_id").is("5c5040cb-d239-4710-aa51-1b0023cdecd4"));
		MongoEntity e = mongoTemplate.findOne(query, MongoEntity.class, "deviceOriginalData");
		return e;
	}
}