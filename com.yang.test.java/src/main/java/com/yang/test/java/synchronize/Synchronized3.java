package com.yang.test.java.synchronize;

public class Synchronized3 {

	public static Synchronized3 s = new Synchronized3();
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new R1()).start();
		new Thread(new R2()).start();
	}

	private static class R1 implements Runnable {
		public void run() {
			s.test();
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			s.test();
		}
	}

	private synchronized void test() {
		System.out.println(1);
	}
}