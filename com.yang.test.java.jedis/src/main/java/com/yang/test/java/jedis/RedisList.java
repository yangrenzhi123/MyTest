package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisList {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("192.168.197.81", 6379);

		StringBuilder sb = new StringBuilder();
		for(int i=0;i<10000;i++) {
			sb.append(", ");
			sb.append("'");
			sb.append(String.format("%05d", i));
			sb.append("'");
		}
		
        String script = "redis.call('lpush', 'ltest'"+sb+")";
        j.eval(script);
		
		
		//j.lpush("ltest", "1");
		for(String s : j.lrange("ltest", 0, -1)) {
			System.out.println(s);
		}
		

//		String t = j.lpop("ltest");
//		System.out.println(t);
		

//		for(String s : j.lrange("ltest", 0, -1)) {
//			System.out.println(s);
//		}

		j.close();
	}
}