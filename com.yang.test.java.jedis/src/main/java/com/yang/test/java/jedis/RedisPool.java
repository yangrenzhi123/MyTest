package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	public static void main(String[] args) throws IOException {
		String key = "testKey";
		
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		
		// 获得连接池
		JedisPool jedisPool = new JedisPool(config, "192.168.10.230", 7001);
		for(int i=0;i<10;i++) {
			Jedis j = jedisPool.getResource();
			System.out.println(j.get(key));
			j.close();
		}
		jedisPool.close();
		

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.20", 7001));
//		nodes.add(new HostAndPort("192.168.10.20", 7002));
//		nodes.add(new HostAndPort("192.168.10.20", 7003));
//		nodes.add(new HostAndPort("192.168.10.22", 7004));
//		nodes.add(new HostAndPort("192.168.10.22", 7005));
//		nodes.add(new HostAndPort("192.168.10.22", 7006));
//		JedisCluster j = new JedisCluster(nodes, config);
//		for(int i=0;i<10;i++) {
//			System.out.println(j.get(key));
//		}
	}
}