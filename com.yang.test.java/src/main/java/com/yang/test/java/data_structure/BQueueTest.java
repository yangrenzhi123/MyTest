package com.yang.test.java.data_structure;

import java.util.LinkedList;
import java.util.Queue;

public class BQueueTest {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		queue.offer("d");
		queue.offer("e");
		queue.offer("f");
		
		String v = queue.poll();
		System.out.println(v);
		for(String a : queue) {
			System.out.println(a);
		}
	}
}