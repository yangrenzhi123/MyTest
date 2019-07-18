package com.example.demo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

	/* 无负载均衡效果 */
	@Autowired
	private DiscoveryClient client;

	/* 带负载均衡效果 */
	@Autowired
	private LoadBalancerClient balanceClient;

	@RequestMapping("/")
	public String index() {
		return "1";
	}

	@RequestMapping("/11111111111111")
	public String getWord() {
		ServiceInstance si = balanceClient.choose("mmb-eureka-client-provider");
		URI uri = si.getUri();
		System.out.println(uri);
		return new RestTemplate().getForObject(uri, String.class);
	}
}