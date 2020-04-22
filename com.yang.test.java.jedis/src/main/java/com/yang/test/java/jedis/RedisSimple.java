package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisSimple {
	public static void main(String[] args) throws IOException {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(30);
//		config.setMaxIdle(10);
//		config.setMaxWaitMillis(2000);
//		JedisPool jp = new JedisPool(config, "172.17.202.149", 7001, 2000, "123456");
//		Jedis j = jp.getResource();

//		Jedis j = new Jedis("192.168.10.92", 6379);
		
//		Jedis j = new Jedis("192.168.10.92", 6379);j.select(1);

		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.10.20", 7001));
		nodes.add(new HostAndPort("192.168.10.20", 7002));
		nodes.add(new HostAndPort("192.168.10.20", 7003));
		nodes.add(new HostAndPort("192.168.10.22", 7004));
		nodes.add(new HostAndPort("192.168.10.22", 7005));
		nodes.add(new HostAndPort("192.168.10.22", 7006));
		JedisCluster j = new JedisCluster(nodes);
		
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.240", 7001));
//		nodes.add(new HostAndPort("192.168.10.240", 7002));
//		nodes.add(new HostAndPort("192.168.10.240", 7003));
//		nodes.add(new HostAndPort("192.168.10.240", 7004));
//		nodes.add(new HostAndPort("192.168.10.240", 7005));
//		nodes.add(new HostAndPort("192.168.10.240", 7006));
//		JedisCluster j = new JedisCluster(nodes);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.228", 27001));
//		nodes.add(new HostAndPort("192.168.10.228", 27002));
//		nodes.add(new HostAndPort("192.168.10.228", 27003));
//		nodes.add(new HostAndPort("192.168.10.229", 27004));
//		nodes.add(new HostAndPort("192.168.10.229", 27005));
//		nodes.add(new HostAndPort("192.168.10.229", 27006));
//		JedisCluster j = new JedisCluster(nodes);
		
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("172.17.19.44", 7001));
//		nodes.add(new HostAndPort("172.17.19.35", 7002));
//		nodes.add(new HostAndPort("172.17.19.40", 7003));
//		nodes.add(new HostAndPort("172.17.19.37", 7004));
//		nodes.add(new HostAndPort("172.17.202.152", 7005));
//		nodes.add(new HostAndPort("172.17.19.39", 7006));
//		nodes.add(new HostAndPort("172.17.19.34", 7007));
//		nodes.add(new HostAndPort("172.17.19.43", 7008));
//		nodes.add(new HostAndPort("172.17.19.45", 7009));
//		JedisCluster j = new JedisCluster(nodes);
		//connectionTimeout：表示连接超时时间
		//soTimeout：表示读取数据超时时间
		//maxAttempts：超时重试次数
//		JedisCluster j = new JedisCluster(nodes, 15000, 10000, 1, "123456", new GenericObjectPoolConfig());

		List<String> l = new ArrayList<>();
		l.add("h_equipment_map:00012181105200");
		for(String key : l) {
			j.del(key);
		}

//		Map<String, String> m = j.hgetAll("h_threshold_rule_map6a70acdf-33e5-4b9f-8e39-d839aef905ac:3");
//		System.out.println(m);

//		String key = "jpush:identifycode:17867930259";
//		System.out.print(j.get(key));
		
//		String key = "jpush:identifycode:17867930259";
//		j.set(key, "www.baidu.com");
//		System.out.println(j.get(key));
		
//		long a = System.currentTimeMillis();
//		int i = 0;
//		Set<String> keys = j.keys("1111111_*");
//		for(String key : keys) {
//			j.del(key);
//			i = i + 1;
//		}
//		System.out.println("耗时：" + (System.currentTimeMillis() - a) + "，数据量：" + i);
		
//		j.set(key, key, "NX", "EX", 2*60);

//		Map<String, String> m = new HashMap<String, String>();
//		m.put("1", "1");
//		j.hmset(key, m);

		j.close();
//		jp.close();
	}
}