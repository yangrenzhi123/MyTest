package com.yang.test.java.mongodb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.yang.test.java.mongodb.model.GwRequestInfo;

@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
public class ZStongji2 {


	public static final ObjectMapper mapper = new ObjectMapper();
	public static long byteNum = 0;
	
	public static final Map<String, Data> uriCount = new HashMap();
	
	public static void main(String[] args) throws ParseException {
		MongoClient mongoClient = new MongoClient("192.168.10.19", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection c = mgdb.getCollection("gwRequestInfo");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		BasicDBObject query = new BasicDBObject();
		query.put("startTime", new BasicDBObject("$gte", df.parse("2020-11-17")).append("$lt", df.parse("2020-11-18")));
		
		//System.out.println(c.count(query));
		
		FindIterable<Document> iter = c.find(query);//.limit(100);
		iter.forEach(new Block<Document>() {
			public void apply(Document _doc) {
				GwRequestInfo grades = null;
				try {
					grades = mapper.readValue(_doc.toJson(), GwRequestInfo.class);
					byteNum = byteNum + grades.getLength().get$numberLong();
					
					String uri = (String)_doc.get("URI");
					String images = "/api-file/images";
					if(uri.startsWith(images)) {
						Data a = uriCount.get(images);
						if(a == null) {
							a = new Data(images, 1, grades.getCostTime().get$numberLong());
							uriCount.put(images, a);
						}else {
							a.countIncrease();
							a.addTotalCosttime(grades.getCostTime().get$numberLong());
						}
					}else {
						Data a = uriCount.get(uri);
						if(a == null) {
							a = new Data(uri, 1, grades.getCostTime().get$numberLong());
							uriCount.put(uri, a);
						}else {
							a.countIncrease();
							a.addTotalCosttime(grades.getCostTime().get$numberLong());
						}
					}
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(byteNum);

		mongoClient.close();


		
		List<Data> l = new ArrayList<>();
		Iterator<Entry<String, Data>> zz = uriCount.entrySet().iterator();
		while (zz.hasNext()) {
			Map.Entry<String, Data> entry = (Map.Entry<String, Data>) zz.next();
			Object key = entry.getKey();
			Data val = (Data) entry.getValue();
			System.out.println(key + "：" + val.getCount() + "，" + val.getTotalCosttime());
			l.add(val);
		}

		Collections.sort(l, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				if (o1.getCount() > o2.getCount()) {
					return -1;
				} else if (o1.getCount() < o2.getCount()) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		System.out.println(l);
	}
}

class Data{
	private String uri;
	private int count;
	private long totalCosttime;
	public Data(String uri, int count, long totalCosttime) {
		super();
		this.uri = uri;
		this.count = count;
		this.totalCosttime = totalCosttime;
	}
	public void countIncrease() {
		this.count++;
	}
	public void addTotalCosttime(long time) {
		this.totalCosttime = this.totalCosttime + time;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getTotalCosttime() {
		return totalCosttime;
	}
	public void setTotalCosttime(long totalCosttime) {
		this.totalCosttime = totalCosttime;
	}
	@Override
	public String toString() {
		return "[uri=" + uri + ", count=" + count + ", totalCosttime=" + totalCosttime + ", one="+totalCosttime/count+"]";
	}
}