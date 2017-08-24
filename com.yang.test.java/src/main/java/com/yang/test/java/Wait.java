package com.yang.test.java;

public class Wait {

	public static void main(String[] args) throws InterruptedException {
		synchronized (Wait.class) {
			Wait.class.wait();
		}
	}
}