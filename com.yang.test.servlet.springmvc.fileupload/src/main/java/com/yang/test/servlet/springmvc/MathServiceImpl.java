package com.yang.test.servlet.springmvc;

import org.springframework.stereotype.Service;

@Service
public class MathServiceImpl implements MathService {
	public long add(int p1, int p2) {
		System.out.println(p1 + p2);
		return p1 + p2;
	}

	public long minus(int p3, int p4) {
		System.out.println(p3 - p4);
		return p3 - p4;
	}

}
