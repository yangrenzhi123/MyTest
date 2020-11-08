package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

public class SlowsqlToEsTimely {
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://"+System.getProperty("mysqlIpAndPort")+"/mysql?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = System.getProperty("mysqlUser");
	static final String PASS = System.getProperty("mysqlPassword");
	
	public static void main(String[] args) throws SQLException, IOException {
		Connection conn = null;
		RestHighLevelClient client = null;
		try {		
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB_URL2, USER, PASS);
			conn.setAutoCommit(true);
			
			client = new RestHighLevelClient(RestClient.builder(new HttpHost(System.getProperty("esIp"), Integer.parseInt(System.getProperty("esPort")), "http")));
			
			t(conn, client);
		}catch(Exception e) {
			e.printStackTrace();
		}

		if(conn != null) conn.close();
		if(client != null) client.close();
	}
	
	public static void t(Connection conn, RestHighLevelClient client) throws ClassNotFoundException, SQLException, IOException, ParseException {
		System.out.println(System.getProperty("mysqlIpAndPort"));
		System.out.println(System.getProperty("mysqlUser"));
		System.out.println(System.getProperty("mysqlPassword"));
		System.out.println(System.getProperty("indexNamePre"));
		System.out.println(System.getProperty("esIp"));
		System.out.println(System.getProperty("esPort"));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar tomrrow = Calendar.getInstance();
		tomrrow.add(Calendar.DAY_OF_YEAR, 1);
		Calendar today = Calendar.getInstance();
		
		String strTomrrow = df.format(tomrrow.getTime());
		String strToday = df.format(today.getTime());
//		String strYestoday = "2020-11-01";
//		String strToday = "2020-11-02";

		String sql = 
		"SELECT"+
		"	CONVERT( sql_text USING utf8 ) AS sql_text,"+
		"	query_time,"+
		"	start_time,"+
		"	user_host,"+
		"	lock_time,"+
		"	rows_sent,"+
		"	rows_examined,"+
		"	db,"+
		"	last_insert_id,"+
		"	insert_id,"+
		"	server_id,"+
		"	thread_id"+
		" FROM"+
		"	mysql.slow_log"+
		" WHERE '"+strToday+"' <= start_time and start_time < '"+strTomrrow+"'"+
		" ORDER BY"+
		"	query_time DESC";
		
		
		BulkRequest bulkRequest = new BulkRequest();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		String indexName = System.getProperty("indexNamePre") + strToday;
		System.out.println("indexName：" + indexName);
		int i = 0;
		int j = 0;
		while (rs.next()) {
			j++;
			if(i++ > 10000) {
				client.bulk(bulkRequest, RequestOptions.DEFAULT);
				bulkRequest = new BulkRequest();
				i = 0;
			}
			
			String sql_text = rs.getString("sql_text");
			sql_text = sql_text.replaceAll("\r", " ");
			sql_text = sql_text.replaceAll("\n", " ");
			sql_text = sql_text.replaceAll("\t", " ");
			sql_text = sql_text.replaceAll("\"", "'");
			sql_text = sql_text.replaceAll("\\\\'", "aaab");
			if(sql_text.length() > 10000) {
				sql_text = sql_text.substring(0, 10000);
			}
			
			String query_time = rs.getString("query_time");
			String user_host = rs.getString("user_host");
			int s0 = 0;
			int s1 = 0;
			int s2 = 0;
			int s3 = 0;
			int s4 = 0;
			int s5 = 0;
			int s6 = 0;
			if(!user_host.contains("192.168.10.")) {
				s6 = 1;
			}else if(query_time.startsWith("00:00:")) {
				s0 = 1;
			}else if(query_time.startsWith("00:01:")) {
				s1 = 1;
			}else if(query_time.startsWith("00:02:")) {
				s2 = 1;
			}else if(query_time.startsWith("00:03:")) {
				s3 = 1;
			}else if(query_time.startsWith("00:04:")) {
				s4 = 1;
			}else {
				s5 = 1;
			}
			String start_time_s = rs.getString("start_time");
			String start_time = rs.getString("start_time").split("\\.")[0].replaceAll(" ", "T");
			String lock_time = rs.getString("lock_time");
			String rows_sent = rs.getString("rows_sent");
			String rows_examined = rs.getString("rows_examined");
			String db = rs.getString("db");
			String last_insert_id = rs.getString("last_insert_id");
			String insert_id = rs.getString("insert_id");
			String server_id = rs.getString("server_id");
			String thread_id = rs.getString("thread_id");
			
			Calendar start = Calendar.getInstance();
			start.setTime(df2.parse(start_time));
			start.add(Calendar.HOUR_OF_DAY, -8);

			boolean existing = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
			if (existing) {
				client.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
			}

			IndexRequest request = new IndexRequest(indexName);
			String jsonString = "{\"@timestamp\":\""+df2.format(start.getTime())+"\",\"start_time\":\""+start_time_s+"\",\"user_host\":\""+user_host+"\",\"lock_time\":\""+lock_time+"\",\"rows_sent\":\""+rows_sent+"\",\"rows_examined\":\""+rows_examined+"\",\"db\":\""+db+"\",\"last_insert_id\":\""+last_insert_id+"\",\"insert_id\":\""+insert_id+"\",\"server_id\":\""+server_id+"\",\"thread_id\":\""+thread_id+"\",\"query_time\":\""+query_time+"\",\"sql_text\":\""+sql_text+"\",\"s0\":"+s0+",\"s1\":"+s1+",\"s2\":"+s2+",\"s3\":"+s3+",\"s4\":"+s4+",\"s5\":"+s5+",\"s6\":"+s6+"}";
			request.source(jsonString, XContentType.JSON);
			bulkRequest.add(request);
		}
		System.out.println("总数据量：" + j);
		client.bulk(bulkRequest, RequestOptions.DEFAULT);
	}
}