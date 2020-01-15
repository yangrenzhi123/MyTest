package com.yang.test.java.springcloud.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("mmb-eureka-client-provider")
public interface NounClient {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String getWord(@RequestHeader(value = "headerId") String headerId);

	@RequestMapping("/formdata")
	String formdata(@RequestParam("test") String test);
}