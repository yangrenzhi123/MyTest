package com.yang.test.java.consul.comsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulComsumer {

	@Autowired
	RestTemplate restTemplate;

	@Autowired(required = true)
	private LoadBalancerClient loadBalancer;

	@Autowired(required = true)
	private DiscoveryClient discoveryClient;

	@RequestMapping("/discover")
	public Object discover() {
		ServiceInstance s = loadBalancer.choose("consul-producer");
		
		return s.getUri().toString();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public String add() {
		return restTemplate.getForEntity("http://consul-producer/", String.class).getBody();
	}

	@RequestMapping("/services")
	public Object services() {
		return discoveryClient.getInstances("consul-producer");
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulComsumer.class, args);
	}
}