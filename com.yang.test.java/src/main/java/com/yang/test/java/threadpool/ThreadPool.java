package com.yang.test.java.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService p = null;
		
		//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		ThreadFactory tf = new ThreadFactory() {
		    public Thread newThread(Runnable r) {
		        return new Thread(r, "线程池中的某线程");
		    }
		};
		Runnable r = new Runnable() {
			public void run() {
				GetAllThread.countByName("线程池中的某线程");
			}
		};
		p = Executors.newCachedThreadPool(tf);
		p.execute(r);
		p.shutdown();

		//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		p = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			p.execute(new Runnable() {
				public void run() {
					System.out.println(1);
				}
			});
		}
		p.shutdown();

		p = Executors.newWorkStealingPool(10);
		p.execute(new Runnable() {
			public void run() {
				System.out.println(1);
			}
		});
		p.shutdown();
		System.out.println();
	}
}