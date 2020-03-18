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
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestTransaction {

	/** 重点提示：db.adminCommand({setFeatureCompatibilityVersion:'4.0'})*/
	public static void main(String[] args) throws ParseException {
		List<ServerAddress> l = new ArrayList<ServerAddress>();
		l.add(new ServerAddress("m1", 27017));
		l.add(new ServerAddress("m2", 27017));
		l.add(new ServerAddress("m3", 27017));

		MongoClient mongoClient = new MongoClient(l);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection c1 = mgdb.getCollection("t1");
		MongoCollection c2 = mgdb.getCollection("t2");
		
		ClientSession cs = mongoClient.startSession();
		cs.startTransaction();
		try {
			doUpdate(mongoClient, cs, c1, c2);
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
		insert.put("_id", 2);
		insert.put("name", "kobe");
		c1.insertOne(cs, insert);

//		int a = 1;
//		if(a == 1) {
//			throw new RuntimeException();
//		}
		
		insert = new Document();
		insert.put("_id", 2);
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
}