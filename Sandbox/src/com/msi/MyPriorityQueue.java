package com.msi;

import java.util.Random;

public class MyPriorityQueue {
	
	private QueueNode root;
	private final static Random rand = new Random();
	
	public void doStuff() {
		addWithPriority(rand.nextInt(100), rand.nextInt(10));
		addWithPriority(10, rand.nextInt(10));
		addWithPriority(rand.nextInt(100), rand.nextInt(10));
		addWithPriority(rand.nextInt(100), rand.nextInt(10));
		addWithPriority(rand.nextInt(100), rand.nextInt(10));
		addWithPriority(rand.nextInt(100), rand.nextInt(10));
		addWithPriority(rand.nextInt(100), rand.nextInt(10));
		
		QueueNode n = takeFront();
		while (!isEmpty()) {
			if (n != null)
				System.out.println(n.data + " with priority: " + n.priority);
			n = takeFront();
		}
	}
	
	public void addWithPriority(int data, int priority) {
		if (root == null) {
			root = new QueueNode(data, priority);
			return;
		}
		QueueNode node = root;
		while (node.next != null)
			node = node.next;
		node.next = new QueueNode(data, priority);
	}
	
	public QueueNode takeFront() {
		if (root == null)
			throw new IllegalStateException("No nodes exist in the queue");
		QueueNode n = root;
		root = n.next;
		return n;
	}
	
	public QueueNode takeWithValue(int data) {
		if (root == null)
			throw new IllegalStateException("No nodes exist in the queue");
		QueueNode node = root, prev = null;
		
		while (node != null) {
			if (node.data == data) {
				break;
			}
			prev = node;
			node = node.next;
		}
		if (prev != null && node != null)
			prev.next = node.next;
		if (prev == null && node != null)
			root = node.next;
		return node;
	}
	
	public QueueNode takeHighestPriority() {
		if (root == null)
			throw new IllegalStateException("No nodes exist in the queue");
		QueueNode hi = root, node = root, prev = null, tmp = null;
		
		while (node.next != null) {
			tmp = node;
			node = node.next;
			if (node.priority > hi.priority) {
				hi = node;
				prev = tmp;
			}
		}
		
		if (prev != null)
			prev.next = hi.next;
		else
			root = hi.next;
		return hi;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public static class QueueNode {
		public int data;
		public int priority;
		public QueueNode next;
		
		public QueueNode(int data, int priority) {
			this.data = data;
			this.priority = priority;
		}
	}

}
