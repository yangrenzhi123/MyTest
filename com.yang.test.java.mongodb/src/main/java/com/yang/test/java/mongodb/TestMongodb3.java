package com.yang.test.java.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.DeleteManyModel;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestMongodb3 {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("192.168.10.239", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("yrz_test");

		MongoCollection mongoCollection = null;
		String collection = null;

		collection = "test";
		mongoCollection = mgdb.getCollection(collection);

		List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
		
		
		Document insert = new Document();
		insert.put("_id", 1);
		insert.put("name", "kobe");
		InsertOneModel<Document> iom1 = new InsertOneModel<Document>(insert);
		insert = new Document();
		insert.put("_id", 2);
		insert.put("name", "kobe");
		InsertOneModel<Document> iom2 = new InsertOneModel<Document>(insert);
		

		Document update = new Document();
		update.put("_id", 3);
		Document updated = new Document();
		updated.put("name", "James");
		UpdateManyModel<Document> umm = new UpdateManyModel<Document>(update, new Document("$set", updated), new UpdateOptions().upsert(true));

		DeleteManyModel<Document> dmm1 = new DeleteManyModel<Document>(new Document("_id", 1));
		DeleteManyModel<Document> dmm2 = new DeleteManyModel<Document>(new Document("_id", 3));

		requests.add(iom1);
		requests.add(iom2);
		requests.add(umm);
		requests.add(dmm1);
		requests.add(dmm2);
		BulkWriteResult bulkWriteResult = mongoCollection.bulkWrite(requests);
		System.out.println(bulkWriteResult.toString());

		mongoClient.close();
	}
}