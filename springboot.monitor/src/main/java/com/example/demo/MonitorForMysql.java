package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorForMysql {

	final static Logger logger = LoggerFactory.getLogger(MonitorForMysql.class);
	
	@Autowired
	Config config;

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public void execute() throws ClassNotFoundException, SQLException {
		List<String> l = config.getDbList();

		Class.forName(DRIVER);

		for (String s : l) {
			MoniResult result = new MoniResult();
			result.setName("Mysql");
			result.setCheckTime(new Date());

			String[] infos = s.split(" ");
			String DB_URL = "jdbc:mysql://" + infos[0] + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";

			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL, infos[1], infos[2]);
			} catch (Exception e) {
				logger.error("", e);
				result.setResult(0);
				DemoApplication.result.put(infos[0] + " " + infos[1] + " ******", result);
				continue;
			}

			conn.setAutoCommit(true);
			PreparedStatement stmt = conn.prepareStatement("select user()");
			boolean rs = stmt.execute();
			stmt.close();
			conn.close();

			if (rs) {
				result.setResult(1);
				DemoApplication.result.put(infos[0] + " " + infos[1] + " ******", result);
			} else {
				result.setResult(0);
				DemoApplication.result.put(infos[0] + " " + infos[1] + " ******", result);
			}
		}
	}
}