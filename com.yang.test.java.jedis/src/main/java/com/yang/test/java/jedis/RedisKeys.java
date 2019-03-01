package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisKeys {
	public static void main(String[] args) throws IOException {
		Jedis j1 = new Jedis("192.168.10.20", 7001);
		Jedis j2 = new Jedis("192.168.10.20", 7002);
		Jedis j3 = new Jedis("192.168.10.20", 7003);
		Jedis j4 = new Jedis("192.168.10.22", 7004);
		Jedis j5 = new Jedis("192.168.10.22", 7005);
		Jedis j6 = new Jedis("192.168.10.22", 7006);

		Set<String> s1 = j1.keys("freeGarbagebagPull:*");
		Set<String> s2 = j2.keys("freeGarbagebagPull:*");
		Set<String> s3 = j3.keys("freeGarbagebagPull:*");
		Set<String> s4 = j4.keys("freeGarbagebagPull:*");
		Set<String> s5 = j5.keys("freeGarbagebagPull:*");
		Set<String> s6 = j6.keys("freeGarbagebagPull:*");
		
		for(String s : s1) {
			System.out.println(j1.get(s));
		}
		for(String s : s2) {
			System.out.println(j1.get(s));
		}
		for(String s : s3) {
			System.out.println(j1.get(s));
		}
		for(String s : s4) {
			System.out.println(j1.get(s));
		}
		for(String s : s5) {
			System.out.println(j1.get(s));
		}
		for(String s : s6) {
			System.out.println(j1.get(s));
		}
		
		
		j1.close();
		j2.close();
		j3.close();
		j4.close();
		j5.close();
		j6.close();
	}
}