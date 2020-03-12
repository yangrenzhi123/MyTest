package com.yang.test.java.erchashu;

import java.util.LinkedList;
import java.util.Queue;

public class TestErChaShu {

	static Node root;

	public static void main(String[] args) {
		addNode(1);
		addNode(9);
		addNode(8);
		addNode(7);
		addNode(5);
		addNode(0);
		traverseLevelOrder(root);
//		containNode(6);
	}

	public static void list(Node root) {
		System.out.println(root.data);
	}

	public static void addNode(int value) {
		root = addNode(root, value);
	}
	private static Node addNode(Node current, int value) {
		if (current == null) {
			return new Node(value);
		}
		if (value < current.data) {
			current.left = addNode(current.left, value);
		} else if (value > current.data) {
			current.right = addNode(current.right, value);
		} else {
			return current;
		}
		return current;
	}

	public static boolean containNode(int value) {
		return containNode(root, value);
	}
	private static boolean containNode(Node current, int value) {
		if (current == null) {
			return false;
		}
		if (value == current.data) {
			return true;
		}
		return value < current.data ? containNode(current.left, value) : containNode(current.right, value);
	}

	public Node deleteNode(Node current, int value) {
		if (current == null) {
			return null;
		}
		if (value == current.data) {
			if (current.left == null && current.right == null) {
				return null;
			}
			if (current.left == null) {
				return current.right;
			}
			if (current.right == null) {
				return current.left;
			}
			int smallestValue = findSmallestValue(current.right);
			current.data = smallestValue;
			current.right = deleteNode(current.right, smallestValue);
			return current;
		}
		if (value < current.data) {
			current.left = deleteNode(current.left, value);
			return current;
		}
		current.right = deleteNode(current.right, value);
		return current;
	}

	private int findSmallestValue(Node root) {
		return root.left == null ? root.data : findSmallestValue(root.right);
	}

	public void traverseInOrder(Node root) {
		if (root != null) {
			traverseInOrder(root.left);
			System.out.println(root.data);
			traverseInOrder(root.right);
		}
	}

	public void traversePreOrder(Node root) {
		if (root != null) {
			System.out.println(root.data);
			traversePreOrder(root.left);
			traversePreOrder(root.right);
		}
	}

	public void traversePostOrder(Node root) {
		if (root != null) {
			traversePostOrder(root.left);
			traversePostOrder(root.right);
			System.out.println(root.data);
		}
	}

	public static void traverseLevelOrder(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> nodes = new LinkedList<Node>();
		nodes.add(root);
		while (!nodes.isEmpty()) {
			Node node = nodes.remove();
			System.out.println(node.data);
			if (node.left != null) {
				nodes.add(node.left);
			}

			if (node.right != null) {
				nodes.add(node.right);
			}
		}
	}
}