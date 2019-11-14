package com.yang.test.java.mongodb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
public class TestMongodb {


	public static final ObjectMapper mapper = new ObjectMapper();
	public static long byteNum = 0;
	
	public static final Map<String, Long> uriCount = new HashMap();
	
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
		query.put("startTime", new BasicDBObject("$gte", df.parse("2019-10-31")).append("$lt", df.parse("2019-11-01")));
		
		System.out.println(c.count(query));
		
		FindIterable<Document> iter = c.find(query);//.limit(100);
		iter.forEach(new Block<Document>() {
			public void apply(Document _doc) {
				GwRequestInfo grades = null;
				try {
					String uri = (String)_doc.get("URI");
					if(uri.startsWith("/api-file/images")) {
						Long a = uriCount.get("/api-file/images");
						if(a == null) {
							a = 1L;
							uriCount.put("/api-file/images", a);
						}else {
							a = a + 1;
							uriCount.put("/api-file/images", a);
						}
					}else {
						Long a = uriCount.get(uri);
						if(a == null) {
							a = 1L;
							uriCount.put(uri, a);
						}else {
							a = a + 1;
							uriCount.put(uri, a);
						}
					}
					
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


		
		List<Long> l = new ArrayList<>();
		Iterator<Entry<String, Long>> zz = uriCount.entrySet().iterator();
		while (zz.hasNext()) {
			Map.Entry<String, Long> entry = (Map.Entry<String, Long>) zz.next();
			Object key = entry.getKey();
			Long val = (Long) entry.getValue();
			System.out.println(key + "：" + val);
			l.add(val);
		}
		
		Collections.sort(l);
		System.out.println(l);
	}
}