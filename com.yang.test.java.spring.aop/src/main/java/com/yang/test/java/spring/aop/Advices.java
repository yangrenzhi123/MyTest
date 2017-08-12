package com.yang.test.java.spring.aop;

import org.aspectj.lang.JoinPoint;

public class Advices {

	public void before(JoinPoint jp) {
		System.out.println("----------前置通知----------");
	}

	public void after(JoinPoint jp) {
		System.out.println("----------最终通知----------");
	}
}