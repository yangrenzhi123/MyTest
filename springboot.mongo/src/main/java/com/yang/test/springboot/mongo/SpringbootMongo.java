package com.yang.test.springboot.mongo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.springboot.mongo.entity.MongoEntity;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class SpringbootMongo {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringbootMongo.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
	@Qualifier(value = "one_MongoTemplate")
	private MongoTemplate oneMongoTemplate;
	@Autowired
	@Qualifier(value = "two_MongoTemplate")
	private MongoTemplate twoMongoTemplate;
	@Autowired
	@Qualifier(value = "three_MongoTemplate")
	private MongoTemplate threeMongoTemplate;

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
			//mongoTemplate.insert(l, "test");
		} catch (org.springframework.dao.DuplicateKeyException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/q")
	public void q() {
		Criteria criteria = Criteria.where("regionid")
				.in(Arrays.asList("000de5db-aa78-479d-8d70-7b3267811568", "04388528-8eb4-41a8-9589-8cf68a7299d8",
						"06c7372e-6c09-475c-9f74-cacb2a050ab6", "07a62a5c-7ae5-4305-a414-fba3ea1e4af5",
						"0c728efd-391a-4496-939d-7777622f8be7", "237c5130-be38-4611-b94c-75a84f19be5f",
						"251c01a7-6847-4f48-978b-e61b6c00bee9", "2a831d64-a317-4a37-8055-47804781a2c2",
						"313a6fb9-cb47-415d-86cd-ca1f02f9d608", "3a61a2b9-3a4f-473a-ac79-a576aacab5d4",
						"3eca810e-1a28-4b8b-8695-b9c6a4e0ae51", "451f6a64-4e1f-4823-a9a5-9191a022fa8b",
						"4e52da86-8566-4b8a-8f46-2c00ab6c9254", "55b50a03-5927-4868-afe4-2c834effcb21",
						"6a648f42-15ae-4678-8fe1-87530b21f4b7", "6a8d5caf-405e-4bfe-925d-61f279e1c2c8",
						"8a5d5a1a-7018-4920-a974-5a720ba91f37", "9dd297d8-b756-4b39-851c-134bdb330824",
						"b889ecfa-597e-44f8-8036-88fbcb2c8d49", "d9765bf6-91c2-4339-bd5e-f70e54e89cae"))
				.and("jlly").in(Arrays.asList(1, 2, 3)).and("cjlx").in(Arrays.asList(-1, 1, 2, 3)).and("ljlx")
				.in(Arrays.asList("DJ", "05", "06", "09", "0A", "0C", "0D", "11"));
		final Query query = new Query();
		query.addCriteria(criteria);

		Calendar start = Calendar.getInstance();
		start.set(2019, 0, 1);
		
		List<Thread> l = new ArrayList<>();
		

		int num = 350;
		final CountDownLatch latch = new CountDownLatch(num);
		
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < num; i++) {
			final String yyyyMMdd = df.format(start.getTime());
			final String collectionName = "h_recycle_record_day_" + yyyyMMdd;
			l.add(new Thread(new Runnable() {
				public void run() {
					long a = System.currentTimeMillis();
					if(yyyyMMdd.startsWith("201901") || yyyyMMdd.startsWith("201904") || yyyyMMdd.startsWith("201907") || yyyyMMdd.startsWith("201910")) {
						oneMongoTemplate.count(query, collectionName);
					}else if(yyyyMMdd.startsWith("201902") || yyyyMMdd.startsWith("201905") || yyyyMMdd.startsWith("201908") || yyyyMMdd.startsWith("201911")) {
						twoMongoTemplate.count(query, collectionName);
					}else {
						threeMongoTemplate.count(query, collectionName);
					}
					System.out.println(collectionName + "，耗时：" + (System.currentTimeMillis() - a));
					latch.countDown();
				}
			}));
			
			start.add(Calendar.DAY_OF_YEAR, 1);
		}
		

		long b = System.currentTimeMillis();
		for(Thread t : l) {
			t.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("总耗时：" + (System.currentTimeMillis() - b));
	}
}