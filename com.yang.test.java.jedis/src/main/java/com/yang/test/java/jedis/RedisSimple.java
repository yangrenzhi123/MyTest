package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisSimple {
	public static void main(String[] args) throws IOException {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(30);
//		config.setMaxIdle(10);
//		config.setMaxWaitMillis(2000);
//		JedisPool jp = new JedisPool(config, "172.17.202.149", 7001, 2000, "123456");
//		Jedis j = jp.getResource();

//		Jedis j = new Jedis("192.168.226.209", 6379);
		
		Jedis j = new Jedis("192.168.10.22", 6379);j.select(1);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.20", 7001));
//		nodes.add(new HostAndPort("192.168.10.20", 7002));
//		nodes.add(new HostAndPort("192.168.10.20", 7003));
//		nodes.add(new HostAndPort("192.168.10.22", 7004));
//		nodes.add(new HostAndPort("192.168.10.22", 7005));
//		nodes.add(new HostAndPort("192.168.10.22", 7006));
//		JedisCluster j = new JedisCluster(nodes);
		
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

//		List<String> l = new ArrayList<>();
//		l.add("h_tenant_group_map:LYZH180317837501");
//		int i = 0;
//		for(String key : l) {
//			//System.out.println(j.hgetAll(key));
//			j.del(key);
//			//j.set(key, "1");
//			i++;
//		}
//		System.out.println("清理数量：" + i);

//		Map<String, String> m = j.hgetAll("appCityInfo");
//		System.out.println(m);

//		String key = "daas_new_push:jiangning_token:";
//		System.out.println(j.get(key));
//		System.out.println(j.ttl(key));
		
//		String key = "testKey";
//		j.set(key, "www.baidu.com");
//		System.out.println(j.get(key));
		
		long a = System.currentTimeMillis();
		int i = 0;
		Set<String> keys = j.keys("mongoCountCache:H_TENANT_GROUP_2503fce6-b923-42ab-9e2a-1d399fae712d_6890342c-eab3-401a-bf95-ab6672a72915*");
		for(String key : keys) {
			try {
				System.out.println(key + "：" + j.get(key));
			}catch(Exception e) {
				System.out.println(key + "：" + j.hgetAll(key));
			}
			//j.del(key);
			i = i + 1;
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - a) + "，数据量：" + i);
		
//		j.set(key, key, "NX", "EX", 2*60);

//		Map<String, String> m = new HashMap<String, String>();
//		m.put("1", "1");
//		j.hmset(key, m);

		j.close();
//		jp.close();
	}
}