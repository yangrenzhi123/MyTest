package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class SlowsqlToEs {

	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.22:3306/mysql?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";
	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yestoday = Calendar.getInstance();
		yestoday.add(Calendar.DAY_OF_YEAR, -1);
		Calendar today = Calendar.getInstance();
		
		String strYestoday = df.format(yestoday.getTime());
		String strToday = df.format(today.getTime());
//		String strYestoday = "2020-10-23";
//		String strToday = "2020-10-24";
		
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(false);
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		

		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));
		
		
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
		" WHERE '"+strYestoday+"' <= start_time and start_time < '"+strToday+"'"+
		" ORDER BY"+
		"	query_time DESC";
		
		

		BulkRequest bulkRequest = new BulkRequest();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next()) {
			if(i++ > 10000) {
				client.bulk(bulkRequest, RequestOptions.DEFAULT);
				bulkRequest = new BulkRequest();
				i = 0;
			}
			
			
			String sql_text = rs.getString("sql_text");
			sql_text = sql_text.replaceAll("\r", " ");
			sql_text = sql_text.replaceAll("\n", " ");
			sql_text = sql_text.replaceAll("\t", " ");
			
			String query_time = rs.getString("query_time");
			String start_time = rs.getString("start_time");
			String user_host = rs.getString("user_host");
			String lock_time = rs.getString("lock_time");
			String rows_sent = rs.getString("rows_sent");
			String rows_examined = rs.getString("rows_examined");
			String db = rs.getString("db");
			String last_insert_id = rs.getString("last_insert_id");
			String insert_id = rs.getString("insert_id");
			String server_id = rs.getString("server_id");
			String thread_id = rs.getString("thread_id");

			IndexRequest request = new IndexRequest("slowquery-mysql-"+strYestoday);
			String jsonString = "{\"sql_text\":\""+sql_text+"\",\"@timestamp\":\""+strYestoday+"\",\"start_time\":\""+start_time+"\",\"user_host\":\""+user_host+"\",\"lock_time\":\""+lock_time+"\",\"rows_sent\":\""+rows_sent+"\",\"rows_examined\":\""+rows_examined+"\",\"db\":\""+db+"\",\"last_insert_id\":\""+last_insert_id+"\",\"insert_id\":\""+insert_id+"\",\"server_id\":\""+server_id+"\",\"thread_id\":\""+thread_id+"\",\"query_time\":\""+query_time+"\"}";
			request.source(jsonString, XContentType.JSON);
			bulkRequest.add(request);

		}
		client.bulk(bulkRequest, RequestOptions.DEFAULT);
		client.close();
	}
}