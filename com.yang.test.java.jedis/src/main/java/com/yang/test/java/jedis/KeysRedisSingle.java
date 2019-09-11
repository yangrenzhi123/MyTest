package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class KeysRedisSingle {
	public static void main(String[] args) throws IOException {
		Jedis j1 = new Jedis("192.168.10.89", 7001);

		Set<String> ss = j1.keys("h_tenant_group_phone_one:*");

		for (String s : ss) {
			System.out.println(s);
		}

		j1.close();
	}
}