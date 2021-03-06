package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class MonitorForRedis {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException, IOException {
		Jedis j1 = null;
		JedisCluster j2 = null;
		
		String s = config.getRedis();
		if(s.contains(",")) {
			Set<HostAndPort> nodes = new HashSet<HostAndPort>();
			for (String it : s.split(",")) {
				String[] ss = it.split(":");
				String ip = ss[0];
				String po = ss[1];
				nodes.add(new HostAndPort(ip, Integer.parseInt(po)));
			}
			j2 = new JedisCluster(nodes);
		}else {
			String[] ss = s.split(":");
			j1 = new Jedis(ss[0], Integer.parseInt(ss[1]));
		}

		String key = "testKey";

		MoniResult result = new MoniResult();
		result.setName("Redis");
		result.setCheckTime(new Date());
		try {
			if(j1 != null) {
				j1.get(key);
				j1.close();
			}else {
				j2.get(key);
				j2.close();
			}
			result.setResult(1);
			MonitorStartup.result.put(s, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			MonitorStartup.result.put(s, result);

			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test(config.getDingDingToken(), yyyy.format(result.getCheckTime()) + "，检测到向redis("+s+")查询key:testKey时发生异常，将于5分钟后再次检测。若异常已修复，该警告不再提醒。");
		}
	}
}