package com.yang.test.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yang.test.java.springcloud.rpc.NounClient;

@RestController
@RefreshScope
public class UserController {

	@Value(value = "${test:null}")
	private String username;

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Autowired
	NounClient nounClient;
	
	@RequestMapping("/1")
	@ResponseBody
	public String getUser() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
		String url = serviceInstance.getUri() + "/";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		return "Invoke : " + url + ", return : " + result;

	}
	
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return nounClient.getWord() + username;
	}
}