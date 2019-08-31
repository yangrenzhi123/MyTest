package com.yang.test.java.jedis;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisBatGet {

	public static TreeSet<String> keys(JedisCluster jedisCluster, String pattern) {
		TreeSet<String> keys = new TreeSet<>();
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(k);
			Jedis connection = jp.getResource();
			try {
				keys.addAll(connection.keys(pattern));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}
		}
		return keys;
	}

	public static void main(String[] args) throws IOException {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.10.240", 7001));
		nodes.add(new HostAndPort("192.168.10.240", 7002));
		nodes.add(new HostAndPort("192.168.10.240", 7003));
		nodes.add(new HostAndPort("192.168.10.240", 7004));
		nodes.add(new HostAndPort("192.168.10.240", 7005));
		nodes.add(new HostAndPort("192.168.10.240", 7006));
		JedisCluster j = new JedisCluster(nodes);

		TreeSet<String> keys = keys(j, "h_tenant_group_map:*");

		

		System.out.println("------------------------------------START-----------------------------------------");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/zhzt.txt"),"UTF-8"));
		
		int i = 0;
		for (String key : keys) {
			i++;
			
			Map<String, String> m = j.hgetAll(key);
            out.write("key："+key+"，value："+m.toString());
            out.newLine();
		}
		out.flush();
		out.close();
		System.out.println(i);
		System.out.println("------------------------------------END-----------------------------------------");

		j.close();
	}
}