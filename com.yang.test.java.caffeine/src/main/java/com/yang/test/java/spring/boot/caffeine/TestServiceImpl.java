package com.yang.test.java.spring.boot.caffeine;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Cacheable(value="getPersonById", sync=true)
	@Override
	public TestDto findById(Integer id) {
		System.out.println("123");
		TestDto t = new TestDto();
		t.setId(id);
		t.setName(UUID.randomUUID().toString());
		return t;
	}
}