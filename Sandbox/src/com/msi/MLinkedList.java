package com.msi;

import java.util.Random;

public class MLinkedList {
	private final static Random rand = new Random();
	
	private Node lroot, rroot;
	
	public void doStuff() {
		lroot = new Node(rand.nextInt(10));
		rroot = new Node(rand.nextInt(10));
		
		String s1 = "", s2 = "";
		
		for (int i = 0; i < 40; i++) {
			int i1 = rand.nextInt(10);
			int i2 = rand.nextInt(10);
			s1 = i1 + s1;
			s2 = i2 + s2;
			add(lroot, i1);
			add(rroot, i2);
		}
		Node n = lroot;
		for (int i = 0; i < 20; i++) n = n.next;
		//n.next = lroot;
		//System.out.println("list " + (detectCycle(lroot) ? "has " : "doesn't have ") + "a cycle");
		printList(lroot);
		sort(lroot);
		printList(lroot);
		/*printList(lroot);
		printList(rroot);
		BigInteger b1 = new BigInteger(s1);
		BigInteger b2 = new BigInteger(s2);
		System.out.println();
		System.out.println(b1.add(b2));
		
		printList(sum(lroot, rroot));*/
	}
	
	private void sort(Node n) {
		Node prev, cur, next;
		int size = size(n);
		Node root = n;
		
		for (int i = 0; i < size; i++) {
			prev = null; cur = root; next = n.next;
			for (int j = 0; j < size; j++) {
				while (next != null && next.val < cur.val) {
					if (prev != null)
						prev.next = next;
					else
						root = next;
					cur.next = next.next;
					next.next = cur;
					cur = next;
					next = next.next;
				}
				prev = cur;
				cur = next;
				if (next != null)
					next = next.next;
				printList(root);
			}
		}
	}
	
	private int size(Node n) {
		if (n == null)
			return 0;
		int size = 1;
		Node node = n;
		while (node.next != null) {
			node = node.next;
			size++;
		}
		return size;
	}
	
	private boolean detectCycle(Node n) {
		Node slow = n, fast = n;
		
		while (slow != null && fast != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast != null)
				fast = fast.next;
			if (slow == fast)
				return true;
		}
		return false;
	}
	
	private void add(Node root, int val) {
		if (root == null)
			return;
		
		Node n = root;
		while (n.next != null)
			n = n.next;
		n.next = new Node(val);
	}
	
	private Node sum(Node left, Node right) {
		Node sum = new Node(0);
		Node s = sum;
		
		while (left != null || right != null || (s != null && s.carry > 0)) {
			s.val = s.carry;
			if (left != null)
				s.val += left.val;
			if (right != null)
				s.val += right.val;
			
			if (s.val > 9) {
				if (s.next == null)
					s.next = new Node();
				s.next.carry = s.val / 10;
				s.val %= 10;
			}
			if (left != null)
				left = left.next;
			if (right != null)
				right = right.next;
				
			if ((left != null || right != null) && s.next == null)
				s.next = new Node();
			s = s.next;
		}
		return reverseList(sum);
		//return sum;
	}
	
	private Node reverseList(Node root) {
		Node cur = root, prev = null, next = null;
		
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
	
	private void printList(Node root) {
		Node n = root;
		while (n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
		System.out.println("");
	}

	private static class Node {
		private Node next;
		private int val, carry;
		
		private Node() {}
		private Node(int val) {
			this.val = val;
		}
	}
}
