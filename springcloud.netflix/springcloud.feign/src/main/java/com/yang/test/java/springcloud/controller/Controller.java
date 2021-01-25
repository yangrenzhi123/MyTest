package com.yang.test.java.springcloud.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.java.springcloud.service.WordService;

@RestController
@RefreshScope
public class Controller {

	@Autowired
	private WordService wordService;

	@RequestMapping("/test")
	public @ResponseBody String test() throws InterruptedException {
		return "success test";
	}
	@RequestMapping("/test2")
	public @ResponseBody String test2() throws InterruptedException {
		return "success test2";
	}

	@RequestMapping("/tttttt")
	public @ResponseBody String index() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int ee = 0;
		int f = 0;
		int g = 0;
		for (int i = 0; i < 20; i++) {
			try {
				String flag = UUID.randomUUID().toString();
				String result = wordService.getNoun(flag);
				if ("已熔断".equals(result)) {
					f++;
				} else {
					g++;
				}
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
		return a + "，" + b + "，" + c + "，" + d + "，" + ee + "，" + f + "，" + g;
	}
}