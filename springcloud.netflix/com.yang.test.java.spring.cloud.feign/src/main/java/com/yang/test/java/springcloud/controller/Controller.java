package com.yang.test.java.springcloud.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.java.springcloud.rpc.NounClient;
import com.yang.test.java.springcloud.service.WordService;

import feign.Feign;
import feign.gson.GsonDecoder;

@RestController
@RefreshScope
public class Controller {

	@Autowired
	private NounClient nounClient;

	@Autowired
	private WordService wordService;

	/* 带负载均衡效果 */
	@Autowired
	private LoadBalancerClient balanceClient;

	@RequestMapping("/1111111111111111111")
	public @ResponseBody String getSentence() {

		ServiceInstance si = balanceClient.choose("mmb-eureka-client-provider");
		URI uri = si.getUri();

		NounClient testFeign1 = Feign.builder().decoder(new GsonDecoder()).target(NounClient.class, uri.toString());
		testFeign1.getWord("123");

		return nounClient.getWord(UUID.randomUUID().toString());
	}

	@RequestMapping("/")
	public @ResponseBody String index() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int ee = 0;
		int f = 0;
		for (int i = 0; i < 20; i++) {
			try {
				String flag = UUID.randomUUID().toString();
				wordService.getNoun(flag);
				f++;
			} catch (feign.RetryableException e) {
				a++;
			} catch (RuntimeException e) {
				Throwable e1 = e.getCause();
				if (e1 instanceof com.netflix.client.ClientException) {
					if (e1.getMessage().startsWith("Load balancer does not have available server for client:")) {
						System.out.println(e1.getMessage());
						b++;
					} else {
						c++;
					}
				} else {
					d++;
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {
				ee++;
			}
		}
		return a + "，" + b + "，" + c + "，" + d + "，" + ee + "，" + f;
	}

	@RequestMapping({"/api-b/2/test1", "/api-b/2/test2"})
	public @ResponseBody String test() {
		return "2";
	}

	@RequestMapping("/formdata")
	public @ResponseBody String formdata() {
		return wordService.getNoun(UUID.randomUUID().toString());
	}
}