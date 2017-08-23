package com.yang.test.java;

/**
 * 测试java对象锁的效果
 * 
 */
public class Synchronized {

	enum E {a}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new R1()).start();
		new Thread(new R2()).start();
	}

	private static class R1 implements Runnable {
		public void run() {
			synchronized (E.a) {
				System.out.println(1);
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			synchronized (E.a) {
				System.out.println(2);
			}
		}
	}
}