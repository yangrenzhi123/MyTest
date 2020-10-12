package com.yang.test.java.springboot.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Tcb")
public interface Tcb {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	void index();
}