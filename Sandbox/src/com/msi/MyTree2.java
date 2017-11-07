package com.msi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;

public class MyTree2 implements Treeterface {
	
	private TreeNode root;
	
	/**
	 * 					9
	 * 			4				15
	 * 		2      7       10        18
	 * 	 1     3              11        19
	 *                           12
	 */
	
	public void doStuff() throws Exception {
		addNode(9);
		addNode(4);
		addNode(15);
		addNode(2);
		addNode(7);
		addNode(10);
		addNode(18);
		addNode(1);
		addNode(3);
		addNode(11);
		addNode(19);
		addNode(12);
		System.out.println("Depth");
		printDepth();
		System.out.println("Breadth");
		printBreadth(root);
		System.out.println("Delete 9");
		deleteNode(15);
		printBreadth(root);
		System.out.println("Tree Height: " + _treeHeight(root));
		System.out.println("");
		System.out.println("Mirrored");
		mirror();
		printBreadth(root);
	}
	
	public void doStuff2() {
		ListNode l1 = new ListNode(17, new ListNode(1));
	    ListNode l2 = new ListNode(3);
	    ListNode l3 = new ListNode(1);
	    ListNode l4 = new ListNode(2);
	    ListNode l5 = new ListNode(16);
	    ListNode l6 = new ListNode(7, new ListNode(3));
	    TreeNode root = new TreeNode(l1, new TreeNode(l2, new TreeNode(l4), null), new TreeNode(l3, new TreeNode(l5), new TreeNode(l6)));
	    ListNode expected = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(7, new ListNode(16, new ListNode(17))))));
	    printBreadth(root);
	    ListNode n = flattenLinkedTree(root);
	    printList(n);
	}
	
	private ListNode flattenLinkedTree(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		traverse(list, root);
		Collections.sort(list);
		ListNode node, rootNode = null;
		if (list.size() > 0) {
			rootNode = new ListNode(list.get(0));
			node = rootNode;
			for (int i = 1; i < list.size(); i++) {
				node.next = new ListNode(list.get(i));
				node = node.next;
			}
		}
		return rootNode;
	}
	
	private void traverse(ArrayList<Integer> list, TreeNode root) {
		if (root == null)
			return;
		
		if (root.value != null) {
			ListNode ln = root.value;
			while (ln != null) {
				if (!list.contains(ln.data))
					list.add(ln.data);
				ln = ln.next;
			}
		}
		traverse(list, root.left);
		traverse(list, root.right);
	}
	
	private void printList(ListNode root) {
		ListNode n = root;
		while (n != null) {
			System.out.print(n.data + " ");
			n = n.next;
		}
		System.out.println("");
	}

	@Override
	public void addNode(int data) {
		if (root == null) {
			root = new TreeNode(data);
			return;
		}
		TreeNode n = root;
		while(true) {
			if (data < n.val) {
				if (n.left == null) { // done
					n.left = new TreeNode(data);
					return;
				}
				n = n.left;
			} else if (data > n.val) {
				if (n.right == null) {
					n.right = new TreeNode(data);
					return;
				}
				n = n.right;
			}
		}
	}

	@Override
	public void deleteNode(int data) {
		deleteNode(data, root);
	}
	
	/**
	 * 					9
	 * 			4				15
	 * 		2      7       10        18
	 * 	 1     3              11        19
	 *                           12
	 */
	private TreeNode deleteNode(int data, TreeNode n) {
		if (n == null)
			return n;
		if (n.val == data) {
			if (n.left == null && n.right == null) {
				return null;
			} else if (n.left == null) {
				return n.right;
			} else if (n.right == null)
				return n.left;
			else {
				TreeNode smallest = _smallestNode(n.right);
				n.val = smallest.val;
				n.right = deleteNode(n.val, n.right);
			}
		} else {
			if (data < n.val)
				n.left = deleteNode(data, n.left);
			else
				n.right = deleteNode(data, n.right);
		}
		return n;
	}
	
	private TreeNode _smallestNode(TreeNode n) {
		if (n.left != null)
			return _smallestNode(n.left);
		return n;
	}

	@Override
	public boolean contains(int data) {
		if (root == null)
			return false;
		TreeNode n = root;
		ArrayBlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(_treeSize(root));
		queue.add(n);
		while(queue.size() > 0) {
			n = queue.poll();
			if (n.val == data)
				return true;
			if (n.left != null)
				queue.add(n.left);
			if (n.right != null)
				queue.add(n.right);
		}
		return false;
	}
	
	public int _treeHeight(TreeNode n) {
		if (n == null)
			return 0;
		return Math.max(_treeHeight(n.left), _treeHeight(n.right)) + 1;
	}

	@Override
	public TreeNode getNode(int data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mirror() {
		_mirror(root);
	}
	
	private void _mirror(TreeNode n) {
		if (n == null)
			return;
		_mirror(n.left);
		_mirror(n.right);
		TreeNode tmp = n.left;
		n.left = n.right;
		n.right = tmp;
	}

	@Override
	public void printDepth() {
		// in-traversal
		_printDepth(root);
		System.out.println("");
	}
	
	private void _printDepth(TreeNode n) {
		if (n == null)
			return;
		
		_printDepth(n.left);
		System.out.print(n.val + " ");
		_printDepth(n.right);
	}
	
	@Override
	public void printBreadth(TreeNode root) {
		if (root == null)
			return;
		TreeNode n = root;
		ArrayBlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(_treeSize(n));
		queue.offer(n);
		while(queue.size() > 0) {
			n = queue.poll();
			if (n.val != 0)
				System.out.print(n.val + " ");
			else
				System.out.print(n.value.data + " ");
			if (n.left != null)
				queue.offer(n.left);
			if (n.right != null)
				queue.offer(n.right);
		}
		System.out.println("");
	}
	
	/**
	 * 					9
	 * 			4				15
	 * 		2      7       10        18
	 * 	 1     3              11        19
	 *                           12
	 */
	private int _treeSize(TreeNode n) {
		if (n == null)
			return 0;
		n.numChildren += _treeSize(n.left);
		n.numChildren += _treeSize(n.right);
		return n.numChildren + 1;
	}

	@Override
	public boolean isBalanced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode leftRotation(TreeNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode rightRotation(TreeNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode leftRightRotation(TreeNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode rightLeftRotation(TreeNode node) {
		// TODO Auto-generated method stub
		return null;
	}

}
