package com.yang.test.java.annotation;

public class Parent extends Yeye{

	@Validate(requierdWithMsg = "true,t����Ϊ��")
	private String t;

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}