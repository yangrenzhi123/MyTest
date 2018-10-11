package com.yang.test.java.jedis;

import redis.clients.jedis.Jedis;

public class App {
	public static void main(String[] args) {
		Jedis j = new Jedis("192.168.30.120", 50010);


		String a = j.set("testKey", "YangRenZhi", "NX", "PX", 60000);
		System.out.println("a:" + a);


		//j.expire("testKey", 0);
		//long a = j.setnx("testKey", "YangRenZhi");
		//System.out.println("a:" + a);
		
		
		
		System.out.println(j.get("testKey"));

		j.close();
	}
}