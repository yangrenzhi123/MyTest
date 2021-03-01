package com.yang.test.java.springcloud.service;

import org.apache.dubbo.config.annotation.Service;

import com.yang.test.java.springcloud.service.DemoService;

@Service(version = "default")
public class DefaultDemoService implements DemoService {

	@Override
	public String sayHello(String name) {
		return "ni hao " + name;
	}
}