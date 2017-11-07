package com.msi;

public class JosephusSurvivor {

	public void doStuff() {
		System.out.println(josephusSurvivor(100, 1));
	}

	public int josephusSurvivor(final int n, final int k) {
		Node root = new Node(1);
		Node node = root, prev;
		for (int i = 1; i < n; i++) {
			node.next = new Node(i + 1);
			node = node.next;
		}
		node.next = root;
		prev = node;
		node = root;
		while (node.val != node.next.val) {
			for (int i = 0; i < k - 1; i++) {
				prev = node;
				node = node.next;
			}
			//istSize(root);
			// node now points to what we're eliminating
			prev.next = node.next;
			node = prev.next;
		}
		return node.val;
	}
	
	private void listSize(Node root) {
		if (root == null) {
			System.out.println("Size: 0");
			return ;
		}
		Node n = root;
		int i = 0;
		while (n.val != root.val) {
			i++;
			n = n.next;
		}
		System.out.println("Size: " + i);
	}
	
	public static class Node {
		public Node next;
		public int val;
		
		public Node(int val) {
			this.val = val;
		}
	}
	
}
