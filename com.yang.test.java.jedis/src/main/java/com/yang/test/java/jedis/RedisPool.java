package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	public static void main(String[] args) throws IOException {
		String key = "testKey";
		
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(2);
		config.setMaxIdle(1);
		config.setMaxWaitMillis(2000);
		
		// 获得连接池
		JedisPool jedisPool = new JedisPool(config, "172.28.51.33", 6379);
		for(int i=0;i<1000;i++) {
			Jedis j = null;
			try {
				j = jedisPool.getResource();
				System.out.println(j.get(key));
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if(j != null) j.close();
			}
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