package com.example.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.bson.Document;
import org.bson.json.Converter;
import org.bson.json.JsonWriterSettings;
import org.bson.json.StrictJsonWriter;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoSlowsqlToEs {

	public static void execute() throws SQLException, IOException {
		JsonWriterSettings settings = JsonWriterSettings.builder().int64Converter(new Converter<Long>() {
			public void convert(Long value, StrictJsonWriter writer) {
				writer.writeNumber(value.toString());
			}
		}).build();

		RestHighLevelClient client = null;
		try {
			client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));
			
			List<Carrier> mongoList = new ArrayList<>();
			mongoList.add(new Carrier(new MongoClient("192.168.10.227", 27017), "192.168.10.227-27017"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.26", 27017), "192.168.10.26-27017"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.19", 27017), "192.168.10.19-27017"));
			
			mongoList.add(new Carrier(new MongoClient("192.168.10.84",27017), "192.168.10.84-27017"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.39",27017), "192.168.10.39-27017"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.227",27018), "192.168.10.227-27018"));
			
			mongoList.add(new Carrier(new MongoClient("192.168.10.17",28017), "192.168.10.17-28017"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.26",28017), "192.168.10.26-28017"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.39",28019), "192.168.10.39-28019"));

			mongoList.add(new Carrier(new MongoClient("192.168.10.84",27018), "192.168.10.84-27018"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.84",27028), "192.168.10.84-27028"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.222",27028), "192.168.10.222-27028"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.222",27038), "192.168.10.222-27038"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.224",27018), "192.168.10.224-27018"));
			mongoList.add(new Carrier(new MongoClient("192.168.10.224",27038), "192.168.10.224-27038"));
			
			
			for(Carrier carrier : mongoList) {
				MongoClient mc = carrier.getMc();
				try {
					t(mc, client, settings, carrier.getHostAndPort());
				} finally {
					mc.close();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		if(client != null) client.close();
	}
	
	public static void t(MongoClient mc, RestHighLevelClient client, JsonWriterSettings settings, String hostAndPort) throws ClassNotFoundException, SQLException, IOException, ParseException {
		MongoIterable<String> iterable = mc.listDatabaseNames();
		MongoCursor<String> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			String db = cursor.next();
			if(!db.equals("admin") && !db.equals("config") && !db.equals("local") && !db.equals("system")) {
				RandomAccessFile raf = new RandomAccessFile("./mongo-slowsql-cursor-"+hostAndPort+"-"+db, "rw");
				FileChannel fc = raf.getChannel();
				ByteBuffer buf = ByteBuffer.allocate(100);
				String last = "1970-01-01 00:00:00";
				int bytesRead;
				do {
					buf.clear();
					bytesRead = fc.read(buf);
					buf.flip();
					byte[] content = new byte[buf.limit()];
					if(content.length > 0) {
						buf.get(content);
						last = new String(content);
					}
				} while (bytesRead != -1);
				
				
				Object[] arr = getSlowsqlFromMongo(mc, db, settings, client, last, hostAndPort);
				@SuppressWarnings("unchecked")
				List<String> l = (List<String>)arr[0];
				if(l.size() > 0) {
					BulkRequest bulkRequest = new BulkRequest();
					for(String json : l) {
						IndexRequest request = new IndexRequest("slowquery-mongo-all");
						request.source(json, XContentType.JSON);
						bulkRequest.add(request);
					}
					client.bulk(bulkRequest, RequestOptions.DEFAULT);
				}
				String lastt = (String)arr[1];
				if(lastt != null) {
					raf.seek(0);
					buf.clear();
					buf.put(lastt.getBytes());
					buf.flip();
					while (buf.hasRemaining()) {
						fc.write(buf);
					}
				}
				raf.close();
				fc.close();
			}
		}
	}

	public static Object[] getSlowsqlFromMongo(MongoClient mc, String db, JsonWriterSettings settings, RestHighLevelClient client, String last, String hostAndPort) throws ParseException, IOException {
		List<String> result = new ArrayList<>();

		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		MongoDatabase mgdb = mc.getDatabase(db);
		MongoCollection<Document> c = mgdb.getCollection("system.profile");

		BasicDBObject query = new BasicDBObject();
		query.put("ts", new BasicDBObject("$gt", df3.parse(last)));

		String lastt = null;
		FindIterable<Document> findIterable = c.find(query);
		MongoCursor<Document> cursor = findIterable.cursor();
		while (cursor.hasNext()) {
			Document d = cursor.next();
			Calendar time = Calendar.getInstance();
			time.setTime((Date)d.get("ts"));
			time.add(Calendar.HOUR_OF_DAY, -8);
			
			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"@timestamp\":\"");json.append(df2.format(time.getTime()));json.append("\"");
			json.append(",\"client\":\"");json.append(d.get("client"));json.append("\"");
			json.append(",\"host\":\"");json.append(hostAndPort);json.append("\"");
			json.append(",\"ns\":\"");json.append(d.get("ns"));json.append("\"");
			json.append(",\"json\":\"");json.append(d.toJson(settings).replaceAll("\\\\\"", "'").replaceAll("\"", "\\\\\""));json.append("\"");
			json.append("}");
			result.add(json.toString());
			
			lastt = df3.format((Date)d.get("ts"));

			// 调试用的代码
			/*try {
				BulkRequest bulkRequest = new BulkRequest();
				IndexRequest request = new IndexRequest("slowquery-mongo-"+strYestoday);
				request.source(json.toString(), XContentType.JSON);
				bulkRequest.add(request);
				client.bulk(bulkRequest, RequestOptions.DEFAULT);
			}catch(Exception e) {
				System.out.println();
			}*/
		}
		return new Object[] {result, lastt};
	}
}

class Carrier{

	private MongoClient mc;
	private String hostAndPort;
	public Carrier(MongoClient mc, String hostAndPort) {
		super();
		this.mc = mc;
		this.hostAndPort = hostAndPort;
	}
	public MongoClient getMc() {
		return mc;
	}
	public void setMc(MongoClient mc) {
		this.mc = mc;
	}
	public String getHostAndPort() {
		return hostAndPort;
	}
	public void setHostAndPort(String hostAndPort) {
		this.hostAndPort = hostAndPort;
	}
}