package com.yang.test.java.jedis.clear.zs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisClear {

	public static TreeSet<String> keys(JedisCluster jedisCluster, String pattern) {
		TreeSet<String> keys = new TreeSet<>();
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(k);

			Jedis connection = jp.getResource();

			try {
				keys.addAll(connection.keys(pattern));
			} finally {
				connection.close();
			}
		}
		return keys;
	}

	public static void main(String[] args) throws IOException {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();

		nodes.add(new HostAndPort("192.168.10.20", 7001));
		nodes.add(new HostAndPort("192.168.10.20", 7002));
		nodes.add(new HostAndPort("192.168.10.20", 7003));
		nodes.add(new HostAndPort("192.168.10.22", 7004));
		nodes.add(new HostAndPort("192.168.10.22", 7005));
		nodes.add(new HostAndPort("192.168.10.22", 7006));

		JedisCluster j = new JedisCluster(nodes);

		TreeSet<String> keys;
		keys = keys(j, "mongoCountCache:H_TENANT_GROUP_*");
		for (String key : keys) {
			j.del(key);
			System.out.println(key);
		}
//		keys = keys(j, "h_tenant_group_greenuser_day_yw_*");
//		for (String key : keys) {
//			j.del(key);
//			System.out.println(key);
//		}
//		keys = keys(j, "h_tenant_group_greenuser_day_*");
//		for (String key : keys) {
//			j.del(key);
//			System.out.println(key);
//		}

		j.close();
	}
}