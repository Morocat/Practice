package com.msi;

public class TreeNode {
	public TreeNode left, right, parent;
	public ListNode value;
	public int val, numChildren;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(ListNode value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public TreeNode(ListNode value) {
		this(value, null, null);
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TreeNode))
			return false;
		return val == ((TreeNode) o).val;
	}
}