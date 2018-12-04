package com.example.demo;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import feign.Feign;
import feign.gson.GsonDecoder;

@RestController
@RefreshScope
public class Controller {

	@Autowired
	private NounClient nounClient;

	@Autowired
	private WordService wordService;


    /*带负载均衡效果*/
    @Autowired
    private LoadBalancerClient balanceClient;
	
	@RequestMapping("/")
	public @ResponseBody String getSentence() {

		ServiceInstance si = balanceClient.choose("mmb-eureka-client-provider");
		URI uri = si.getUri();
		
		
		NounClient testFeign1 = Feign.builder().decoder(new GsonDecoder()).target(NounClient.class, uri.toString());
		testFeign1.getWord("123");
		
		return nounClient.getWord(UUID.randomUUID().toString());
	}
	
	@RequestMapping("/strix")
	public @ResponseBody String getS() {
		String flag = UUID.randomUUID().toString();
		
		System.out.println("flag:"+flag);
		String s = wordService.getNoun(flag);
		return s;
	}
}