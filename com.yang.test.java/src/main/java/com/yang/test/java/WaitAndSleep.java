package com.yang.test.java;

public class WaitAndSleep {

    public static void main(String[] args) {
        new Thread(new R1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new R2()).start();
    }

    private static class R1 implements Runnable {
        public void run() {
            synchronized (WaitAndSleep.class) {

                System.out.println("thread1 is waiting...");
                try {
                    // 调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    WaitAndSleep.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class R2 implements Runnable {
        public void run() {
            synchronized (WaitAndSleep.class) {
                WaitAndSleep.class.notify();
            }
            synchronized (WaitAndSleep.class) {
                System.out.println(1);
            }
        }
    }
}