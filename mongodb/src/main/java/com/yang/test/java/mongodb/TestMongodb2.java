package com.yang.test.java.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class TestMongodb2 {

	public static void main(String[] args) throws InterruptedException {
		MongoClient mongoClient = new MongoClient("192.168.10.239", 27017);
		final MongoDatabase mgdb = mongoClient.getDatabase("test");

		final CountDownLatch latch = new CountDownLatch(10);

		List<Thread> l = new ArrayList<>();
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "deviceOriginalData";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "alarmDataInfo";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "equipHeartBeatData";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "flEquipFaceMongo";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "gwRequestInfo";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "lyznErrorLog";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.countDocuments() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "projectScoreRecordDTO";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "recovery_analysis";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "staticPage";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				long a = System.currentTimeMillis();
				String collectName = "tenantUserMongo";
				MongoCollection c = mgdb.getCollection(collectName);
				System.out.println(collectName + "数据量：" + c.count() + "，查询耗时：" + (System.currentTimeMillis() - a));
				latch.countDown();
			}
		}));

		for(Thread t : l) {
			t.start();
		}
		
		latch.await();
		mongoClient.close();
	}
}