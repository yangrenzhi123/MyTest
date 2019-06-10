package com.yang.test.java.synchronize;

public class Synchronized4String {

	public static Synchronized4String s = new Synchronized4String();
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new R1()).start();
		new Thread(new R2()).start();
	}

	private static class R1 implements Runnable {
		public void run() {
			synchronized ("123") {
				System.out.println(1);
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			String s = new String("123");
			s = s.intern();
			synchronized (s) {
				System.out.println(2);
			}
		}
	}
}