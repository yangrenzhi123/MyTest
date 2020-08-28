package com.yang.test.java.springcloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@EnableDiscoveryClient
@SpringBootApplication
public class RibbonStartup {

	@Bean
	@LoadBalanced
	public RestTemplate rebbionRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonStartup.class, args);
	}
}

@RestController
class RibbonController {
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public String helloRibbon() {
		return restTemplate.getForEntity("http://mmb-eureka-client-provider", String.class).getBody();
	}
}

@Configuration
class RibbonConfiguration {

	@Bean
	public IRule ribbonRule(IClientConfig config) {
		//return new RandomRule();
		//return new RoundRobinRule();
		//return new RetryRule();
		//return new BestAvailableRule();
		//return new AvailabilityFilteringRule();
		return new WeightedResponseTimeRule();//return new ResponseTimeWeightedRule();
		//return new ZoneAvoidanceRule();
	}
}