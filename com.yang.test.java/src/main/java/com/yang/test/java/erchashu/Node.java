package com.yang.test.java.erchashu;

public class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}