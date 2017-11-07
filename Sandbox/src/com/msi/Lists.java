package com.msi;

public class Lists {

	private ListNode lptr;

	public TreeNode init() {
		
		ListNode l1 = new ListNode(17, new ListNode(1));
		ListNode l2 = new ListNode(3);
		ListNode l3 = new ListNode(1);
		ListNode l4 = new ListNode(2);
		ListNode l5 = new ListNode(16);
		ListNode l6 = new ListNode(7, new ListNode(3));
		return new TreeNode(l1, new TreeNode(l2, new TreeNode(l4), null),
				new TreeNode(l3, new TreeNode(l5), new TreeNode(l6)));
	}

	private void printLL(ListNode node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}

	public ListNode flatten(TreeNode root) {
		if (root == null)
			return null;
		
		buildLL(root);
		printLL(root.value);
		root.value = sortLL(root.value);
		printLL(root.value);
		return root.value;
	}

	ListNode sortLL(ListNode node) {
		ListNode root = node, prev, cur, next;
		int size = ll_size(node);

		for (int i = 0; i < size; i++) {
			prev = null;
			cur = root;
			next = cur.next;
			for (int j = 0; next != null; j++) {
				// if cur > next, swap them
				if (cur.data > next.data) {
					if (prev != null) // if not the first element
						prev.next = next;
					cur.next = next.next;
					next.next = cur;

					prev = next;
					next = cur.next;
					if (j == 0)
						root = prev;
				} else {
					prev = cur;
					cur = next;
					next = next.next;
				}
			}
		}
		return root;
	}

	int ll_size(ListNode node) {
		int count = 0;
		while (node != null) {
			count++;
			node = node.next;
		}
		return count;
	}

	void buildLL(TreeNode tnode) {
		if (tnode == null)
			return;
		if (lptr == null) {
			lptr = tnode.value;
		} else {
			lptr.next = tnode.value;
			lptr = lptr.next;
			lptr.next = null;
		}
		buildLL(tnode.left);
		buildLL(tnode.right);
	}

	class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public ListNode value;

		TreeNode(ListNode value, TreeNode left, TreeNode right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		TreeNode(ListNode value) {
			this(value, null, null);
		}
	}

	class ListNode {
		public int data;
		public ListNode next;

		ListNode(int data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		ListNode(int data) {
			this(data, null);
		}
	}
}
