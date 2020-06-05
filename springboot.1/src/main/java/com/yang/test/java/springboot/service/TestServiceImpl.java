package com.yang.test.java.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	public void doTest() {
		System.out.println(1);
	}
}