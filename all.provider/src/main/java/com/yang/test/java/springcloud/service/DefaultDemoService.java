package com.yang.test.java.springcloud.service;

import com.cql.tudou.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "default")
public class DefaultDemoService implements DemoService {

	@Override
	public String sayHello(String name) {
		return "ni hao " + name;
	}
}