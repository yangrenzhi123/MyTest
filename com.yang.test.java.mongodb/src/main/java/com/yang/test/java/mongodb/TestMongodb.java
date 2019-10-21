package com.yang.test.java.mongodb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.bson.Document;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({"rawtypes", "unchecked", "deprecation"})
public class TestMongodb {


	public static final ObjectMapper mapper = new ObjectMapper();
	public static long byteNum = 0;
	
	public static void main(String[] args) throws ParseException {
		//List<ServerAddress> l = new ArrayList<ServerAddress>();
		
//		 l.add(new ServerAddress("192.168.30.120", 27017));
//		 l.add(new ServerAddress("192.168.30.121", 27017));
//		 l.add(new ServerAddress("192.168.10.10", 27017));

		MongoClient mongoClient = new MongoClient("192.168.10.19", 27017);
		// MongoClient mongoClient = new MongoClient(l);

		MongoDatabase mgdb = mongoClient.getDatabase("test");

		MongoCollection c = mgdb.getCollection("gwRequestInfo");
		
//		long a = System.currentTimeMillis();
//		for(int i=0;i<1;i++) {
//			Document doc = Document.parse("{\"myKey\":\"123\"}");
//			c.insertOne(doc);
//		}
//		System.out.println(System.currentTimeMillis() - a);
		

		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		BasicDBObject query = new BasicDBObject();
		query.put("startTime", new BasicDBObject("$gte", df.parse("2019-10-20")).append("$lt", df.parse("2019-10-21")));
		
		System.out.println(c.count(query));
		
		FindIterable<Document> iter = c.find(query);//.limit(100);
		iter.forEach(new Block<Document>() {
			public void apply(Document _doc) {
				GwRequestInfo grades = null;
				try {
					grades = mapper.readValue(_doc.toJson(), GwRequestInfo.class);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				byteNum = byteNum + grades.getLength().get$numberLong();
			}
		});
		System.out.println(byteNum);

		mongoClient.close();
	}
}