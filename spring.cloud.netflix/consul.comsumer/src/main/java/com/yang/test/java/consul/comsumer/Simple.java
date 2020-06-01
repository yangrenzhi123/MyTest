package com.yang.test.java.consul.comsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.ConsulRawClient;

public class Simple {

	public static void main(String[] args) {
		ConsulRawClient client = new ConsulRawClient("192.168.8.70", 8500);
		ConsulClient consul = new ConsulClient(client);
		// 获取所有服务
		consul.getAgentServices().getValue();

		System.out.println();
	}
}