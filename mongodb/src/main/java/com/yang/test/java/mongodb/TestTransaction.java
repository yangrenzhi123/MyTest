package com.yang.test.java.mongodb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestTransaction {

	/** 重点提示：db.adminCommand({setFeatureCompatibilityVersion:'4.0'})*/
	public static void main(String[] args) throws ParseException {
		List<ServerAddress> l = new ArrayList<ServerAddress>();
//		l.add(new ServerAddress("m1", 27017));
//		l.add(new ServerAddress("m2", 27017));
//		l.add(new ServerAddress("m3", 27017));
		l.add(new ServerAddress("192.168.10.228", 28017));
		l.add(new ServerAddress("192.168.10.90", 28017));
		l.add(new ServerAddress("192.168.10.240", 28018));
		MongoClient mongoClient = new MongoClient(l);

		MongoDatabase mgdb = mongoClient.getDatabase("test238");
		MongoCollection c1 = mgdb.getCollection("h_vote_record_activityid_4_test");
		MongoCollection c2 = mgdb.getCollection("t2");
		
		ClientSession cs = mongoClient.startSession();
		cs.startTransaction();
		try {
//			doInsert(mongoClient, cs, c1, c2);
			doBulkInsert(mongoClient, cs, c1);
			doBulkInsert(mongoClient, cs, c1);
			doBulkInsert(mongoClient, cs, c1);
			doBulkInsert(mongoClient, cs, c1);
			cs.commitTransaction();
		}catch(Exception e) {
			e.printStackTrace();
			cs.abortTransaction();
		}finally {
			mongoClient.close();
		}
	}

	/** 更新加锁，后来者直接抛异常 */
	public static void doUpdate(MongoClient mongoClient, ClientSession cs, MongoCollection c1, MongoCollection c2) {
		c1.updateOne(cs, Filters.eq("_id", 2), new Document("$set", new Document("name", "kobe2")));

//		int a = 1;
//		if(a == 1) {
//			throw new RuntimeException();
//		}
		
		c2.updateOne(cs, Filters.eq("_id", 2), new Document("$set", new Document("name", "kobe2")));
	}
	
	/** 不会加锁 */
	public static void doInsert(MongoClient mongoClient, ClientSession cs, MongoCollection c1, MongoCollection c2) {
		Document insert = new Document();
		insert.put("name", "kobe");
		c1.insertOne(cs, insert);

//		int a = 1;
//		if(a == 1) {
//			throw new RuntimeException();
//		}
		
		insert = new Document();
		insert.put("name", "kobe");
		c2.insertOne(cs, insert);
	}
	
	public static void doBulkInsertorupdate(MongoClient mongoClient, ClientSession cs, MongoCollection c1, MongoCollection c2) {
		Document query2 = new Document();
		query2.put("_id", 1);
		Document updated2 = new Document();
		updated2.put("name", "kobe2");
		UpdateManyModel<Document> umm2 = new UpdateManyModel<Document>(query2, new Document("$set", updated2), new UpdateOptions().upsert(true));

		Document query1 = new Document();
		query1.put("_id", 2);
		Document updated = new Document();
		updated.put("name", "kobe2");
		UpdateManyModel<Document> umm = new UpdateManyModel<Document>(query1, new Document("$set", updated), new UpdateOptions().upsert(true));

		List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
		requests.add(umm);
		requests.add(umm2);
		c1.bulkWrite(cs, requests);
		c2.bulkWrite(cs, requests);
		
//		int a = 1;
//		if(a == 1) {
//			throw new RuntimeException();
//		}
	}
	
	public static void doBulkInsert(MongoClient mongoClient, ClientSession cs, MongoCollection c1) {
		long a = System.currentTimeMillis();
		List<InsertOneModel<Document>> l = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			Document insert = new Document();
			insert.put("signuprecordid", 1245187673384161280L);
			insert.put("status", 0);
			insert.put("tenantaccountid", "a9c9ed23-f05d-4ac7-82ce-46d04c962e71");
			insert.put("tplx", 0);
			insert.put("tpsj", 1586160995526L);
			InsertOneModel<Document> iom = new InsertOneModel<Document>(insert);
			l.add(iom);
		}
		c1.bulkWrite(cs, l);
		System.out.println("500插入耗时：" + (System.currentTimeMillis() - a));
	}
}