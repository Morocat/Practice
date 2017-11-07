package com.msi;

public class MyStack {
	
	private Node top;
	private int min, size;
	
	public void doStuff() { 
		/*push(3);
		push(2);
		push(2);
		push(1);
		System.out.println("Min: " + min);
		pop();
		System.out.println("Min: " + min);
		pop();
		System.out.println("Min: " + min);
		pop();
		System.out.println("Min: " + min);*/
		push(5);
		push(9);
		push(3);
		push(4);
		push(8);
		push(2);
		push(6);
		push(7);
		sort();
		print();
	}
	
	public void push(int i) {
		Node tmp = top;
		top = new Node(i);
		top.next = tmp;
	}
	
	public Node pop() {
		Node ret = top;
		if (top != null)
			top = top.next;
		return ret;
	}
	
	public void sort() {
		if (top != null) {
			Node n = pop();
			sort();
			_insert(n);
		}
	}
	
	private void _insert(Node n) {
		if (top == null || n.data > top.data) {
			push(n.data);
			return;
		}
		Node tmp = pop();
		_insert(n);
		push(tmp.data);
	}
	
	public void print() {
		Node n = top;
		System.out.println("Top");
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
		System.out.println("Bottom");
	}

	public static class Node {
		public int data, minHelper = Integer.MAX_VALUE;
		private Node next;
		
		private Node(int data) {
			this.data = data;
		}
	}
	
}
