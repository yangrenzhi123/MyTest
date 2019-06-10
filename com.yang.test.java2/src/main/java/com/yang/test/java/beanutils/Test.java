package com.yang.test.java.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;

public class Test {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		A a = new A();
		a.setI(new Timestamp(System.currentTimeMillis()));

		A b = new A();
		BeanUtils.copyProperties(a, b);

		//b.setI(new Timestamp(0));
		
		System.out.println(a.getI());
		System.out.println(b.getI());
	}
}

class A {
	private Timestamp i;

	public Timestamp getI() {
		return i;
	}

	public void setI(Timestamp i) {
		this.i = i;
	}
}