package com.yang.test.java.data_structure;

import java.util.Stack;

public class AStack {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("a");
		stack.push("b");
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
	}
}