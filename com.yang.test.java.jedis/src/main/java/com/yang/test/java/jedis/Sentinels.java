package com.yang.test.java.jedis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class Sentinels {

	public static void main(String[] args) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(10);
		jedisPoolConfig.setMaxIdle(5);
		jedisPoolConfig.setMinIdle(5);

		Set<String> sentinels = new HashSet<>(Arrays.asList("172.17.202.154:26379", "172.17.202.148:26379", "172.17.202.152:26379"));

		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig);

		Jedis j = pool.getResource();

		j.set("testKeyyy6", "test124");
		System.out.println(j.get("testKeyyy6"));
		
		pool.close();
	}
}