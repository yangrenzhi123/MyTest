package com.yang.test.java.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;

public class Test {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		A a = new A();
		a.setI(new Timestamp(System.currentTimeMillis()));
		a.setK("123");
		a.setJ(2);

		B b = new B();

		BeanUtils.copyProperties(a, b);
		System.out.println(a.getI().hashCode());
		System.out.println(b.getI().hashCode());
		System.out.println(a.getK().hashCode());
		System.out.println(b.getK().hashCode());
		System.out.println(a.getJ().hashCode());
		System.out.println(b.getJ().hashCode());
		System.out.println(a.getI() == b.getI());
	}
}

class A {
	private Timestamp i;
	private Integer j;
	private String k;

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public Timestamp getI() {
		return i;
	}

	public void setI(Timestamp i) {
		this.i = i;
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}
}

class B {
	private Timestamp i;
	private Integer j;
	private String k;

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public Timestamp getI() {
		return i;
	}

	public void setI(Timestamp i) {
		this.i = i;
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}
}