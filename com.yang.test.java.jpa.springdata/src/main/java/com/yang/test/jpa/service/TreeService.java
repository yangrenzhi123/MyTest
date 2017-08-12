package com.yang.test.jpa.service;

import java.util.List;

public interface TreeService {

	List<Long> getSub(Long parentId);
	
	void test();
}