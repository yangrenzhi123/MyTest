package com.yang.test.java;

public class Sun extends Father {

	public static void main(String[] args) {
		new Sun().service();
	}
	
	public void doService() {
		System.out.println("Sun.doService");
	}
}

class Father {

	public void service() {
		doService();
	}
	
	public void doService(){
		System.out.println("Father.doService");
	}
}