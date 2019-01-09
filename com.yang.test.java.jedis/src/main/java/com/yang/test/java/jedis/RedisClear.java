package com.yang.test.java.jedis;

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
		TreeSet<String> keys = new TreeSet<>(); // 获取所有的节点
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes(); // 遍历节点 获取所有符合条件的KEY
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
		nodes.add(new HostAndPort("192.168.30.120", 50010));
		nodes.add(new HostAndPort("192.168.30.120", 50020));
		nodes.add(new HostAndPort("192.168.30.120", 50030));
		nodes.add(new HostAndPort("192.168.30.121", 50040));
		nodes.add(new HostAndPort("192.168.30.121", 50050));
		nodes.add(new HostAndPort("192.168.30.121", 50060));
		JedisCluster j = new JedisCluster(nodes);

		TreeSet<String> keys = keys(j, "*");
		
		for (String key : keys) {
			j.del(key);
		}

		j.close();
	}
}