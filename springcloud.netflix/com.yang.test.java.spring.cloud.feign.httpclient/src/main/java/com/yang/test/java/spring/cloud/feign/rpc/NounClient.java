package com.yang.test.java.spring.cloud.feign.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("mmb-eureka-client-provider")
public interface NounClient {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getWord();
}