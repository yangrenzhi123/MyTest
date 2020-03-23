package com.yang.test.java.mongodb;

import java.text.ParseException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestMapReduce {
	public static void main(String[] args) throws ParseException {
		MongoClient mc = new MongoClient("192.168.8.70", 27017);

		MongoDatabase mgdb = mc.getDatabase("test");
		MongoCollection<Document> c = mgdb.getCollection("t");
		MapReduceIterable<Document> r = c.mapReduce("function() {emit(this.cust_id, this.amount);}", "function(key, values) {return Array.sum(values)}");
		MongoCursor<Document> cursor = r.iterator();
		while (cursor.hasNext()) {
			String json = cursor.next().toJson();
			System.out.println(json);
		}

		mc.close();
	}
}