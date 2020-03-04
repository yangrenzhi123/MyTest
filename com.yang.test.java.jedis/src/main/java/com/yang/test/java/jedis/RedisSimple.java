package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisSimple {
	public static void main(String[] args) throws IOException {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(30);
//		config.setMaxIdle(10);
//		config.setMaxWaitMillis(2000);
//		JedisPool jp = new JedisPool(config, "172.17.202.149", 7001, 2000, "123456");
//		Jedis j = jp.getResource();
		
		Jedis j = new Jedis("172.17.202.156", 7002);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.20", 7001));
//		nodes.add(new HostAndPort("192.168.10.20", 7002));
//		nodes.add(new HostAndPort("192.168.10.20", 7003));
//		nodes.add(new HostAndPort("192.168.10.22", 7004));
//		nodes.add(new HostAndPort("192.168.10.22", 7005));
//		nodes.add(new HostAndPort("192.168.10.22", 7006));
//		JedisCluster j = new JedisCluster(nodes);
		
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.240", 7001));
//		nodes.add(new HostAndPort("192.168.10.240", 7002));
//		nodes.add(new HostAndPort("192.168.10.240", 7003));
//		nodes.add(new HostAndPort("192.168.10.240", 7004));
//		nodes.add(new HostAndPort("192.168.10.240", 7005));
//		nodes.add(new HostAndPort("192.168.10.240", 7006));
//		JedisCluster j = new JedisCluster(nodes);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.228", 27001));
//		nodes.add(new HostAndPort("192.168.10.228", 27002));
//		nodes.add(new HostAndPort("192.168.10.228", 27003));
//		nodes.add(new HostAndPort("192.168.10.229", 27004));
//		nodes.add(new HostAndPort("192.168.10.229", 27005));
//		nodes.add(new HostAndPort("192.168.10.229", 27006));
//		JedisCluster j = new JedisCluster(nodes);
		
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("172.17.202.151", 7001));
//		nodes.add(new HostAndPort("172.17.202.156", 7002));
//		nodes.add(new HostAndPort("172.17.202.158", 7003));
//		nodes.add(new HostAndPort("172.17.202.148", 7004));
//		nodes.add(new HostAndPort("172.17.202.152", 7005));
//		nodes.add(new HostAndPort("172.17.202.147", 7006));
//		nodes.add(new HostAndPort("172.17.202.155", 7007));
//		nodes.add(new HostAndPort("172.17.202.150", 7008));
//		nodes.add(new HostAndPort("172.17.202.153", 7009));
//		JedisCluster j = new JedisCluster(nodes);

//		List<String> l = new ArrayList<>();
//		l.add("h_tenant_group_map:LYZH110227001701");
//		for(String key : l) {
//			j.del(key);
//		}

//		Map<String, String> m = j.hgetAll("h_tenant_group_phone_one:13713696211");
//		System.out.println(m);


		j.set("get_alarmdatazzid_dao", "1000000");
		System.out.println(j.get("get_alarmdatazzid_dao"));
		
//		Set<String> keys = j.keys("*");
//		for(String key : keys) {
//			System.out.println(j.get(key));
//			//j.del(key);
//		}
		
//		j.set(key, key, "NX", "EX", 2*60);

//		Map<String, String> m = new HashMap<String, String>();
//		m.put("1", "1");
//		j.hmset(key, m);

		j.close();
//		jp.close();
	}
}