package com.yang.test.java.springcloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@RequestMapping("/gatewayFallback")
	public String gatewayFallback() {
		return "{\"msg\":\"服务降级\",\"code\":200}";
	}

}