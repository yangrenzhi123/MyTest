package com.yang.test.java.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yang.test.java.springcloud.rpc.NounClient;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	NounClient nounClient;

	@HystrixCommand(fallbackMethod = "getFallbackNoun")
	public String getNoun(String uuid) {
		return nounClient.getWord(uuid);
	}

	public String getFallbackNoun(String uuid) {
		return "1234";
	}
}