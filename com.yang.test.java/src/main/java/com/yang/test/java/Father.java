package com.yang.test.java;

public class Father {

	public void service() {
		System.out.println("Father.service");
		doService();
	}
	
	public void doService(){
		System.out.println("Father.doService");
	}
}