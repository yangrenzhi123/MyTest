package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisKeys2 {
	public static void main(String[] args) throws IOException {
		Jedis j1 = new Jedis("192.168.10.230", 7001);

		Set<String> s1 = j1.keys("h_threshold_rule*");

		for (String s : s1) {
			System.out.println("Key内容：" + s + "，值内容：" + j1.get(s));
		}

		j1.close();
	}
}