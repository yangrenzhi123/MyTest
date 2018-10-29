package com.yang.test.java.spring.aop;

import org.aspectj.lang.JoinPoint;

public class Advices {

	public void before(JoinPoint jp) {
		Object[] a = jp.getArgs();
		long l = a.length;
		if(l > 0) {
			for(int i=0;i<l;i++) {
				System.out.println(a[i].getClass());
			}
		}
		System.out.println("----------前置通知----------");
	}

	public void after(JoinPoint jp) {
		System.out.println("----------最终通知----------");
	}
}