package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.RequestLine;

@FeignClient("${ser.name}")
public interface NounClient {

	@RequestLine("GET /")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getWord(@RequestHeader(value = "headerId") String headerId);
}