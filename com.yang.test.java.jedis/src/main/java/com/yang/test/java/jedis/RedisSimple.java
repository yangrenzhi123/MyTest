package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisSimple {
	public static void main(String[] args) throws IOException {
//		Jedis j = new Jedis("172.28.51.33", 6379);



		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.30.62", 7001));
		nodes.add(new HostAndPort("192.168.30.62", 7002));
		nodes.add(new HostAndPort("192.168.30.62", 7003));
		nodes.add(new HostAndPort("192.168.30.62", 7004));
		nodes.add(new HostAndPort("192.168.30.62", 7005));
		nodes.add(new HostAndPort("192.168.30.62", 7006));
		JedisCluster j = new JedisCluster(nodes);

		String key = "testKey";
		j.set(key, "YangRenZhi");
		System.out.println(j.get(key));

		j.close();
	}
}