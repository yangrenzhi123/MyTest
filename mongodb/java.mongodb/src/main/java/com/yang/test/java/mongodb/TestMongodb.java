package com.yang.test.java.mongodb;

import java.util.Calendar;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TestMongodb {

	public static void main(String[] args) {
		//List<ServerAddress> l = new ArrayList<ServerAddress>();
		
//		 l.add(new ServerAddress("192.168.30.120", 27017));
//		 l.add(new ServerAddress("192.168.30.121", 27017));
//		 l.add(new ServerAddress("192.168.10.10", 27017));

		MongoClient mongoClient = new MongoClient("192.168.10.230", 27017);
		// MongoClient mongoClient = new MongoClient(l);

		MongoDatabase mgdb = mongoClient.getDatabase("test");

		MongoCollection c = mgdb.getCollection("deviceOriginalData");
		
//		long a = System.currentTimeMillis();
//		for(int i=0;i<1;i++) {
//			Document doc = Document.parse("{\"myKey\":\"123\"}");
//			c.insertOne(doc);
//		}
//		System.out.println(System.currentTimeMillis() - a);
		

		
		Calendar s = Calendar.getInstance();
		s.set(Calendar.HOUR_OF_DAY, 0);
		s.set(Calendar.MINUTE, 0);
		s.set(Calendar.SECOND, 0);

		
		Calendar e = Calendar.getInstance();
		e.set(Calendar.DAY_OF_YEAR, e.get(Calendar.DAY_OF_YEAR)+1);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.SECOND, 0);
		
//		BasicDBObject query = new BasicDBObject();
//		query.put("receiveTime", new BasicDBObject("$gte", s.getTime()).append("$lt", e.getTime()));
//		query.put("status", new BasicDBObject("$eq", 0));
		
//		System.out.println(c.count(query));
		
		FindIterable<Document> iter = c.find(/*query*/).limit(1000000);
		iter.forEach(new Block<Document>() {
			public void apply(Document _doc) {
				System.out.println(_doc.toJson());
			}
		});

		mongoClient.close();
	}
}