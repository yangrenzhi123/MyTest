package com.yang.test.java.consul.comsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.ConsulRawClient;

public class Simple {

	public static void main(String[] args) {
		ConsulRawClient client = new ConsulRawClient("172.28.51.33", 8500);
		ConsulClient consul = new ConsulClient(client);
		// 获取所有服务
		consul.getAgentServices().getValue();

		System.out.println();
	}
}