package com.msi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class MyTree implements Treeterface, Iterator<Integer> {

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
		addNode(7);
		addNode(2);
		addNode(3);
		addNode(1);
		addNode(15);
		addNode(18);
		addNode(10);
		addNode(11);
		addNode(19);
		addNode(12);
		printBoundary();
		//printTree(root);
		//printPathsWithSum();
		//System.out.println("LCA: " + lca(2, 4));
		//deleteNode(3);
		//printDepth();
		//deleteNode(15);
		//printDepth();
		/*root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(-3);*/
		
		/*root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(-2);
		root.left.right = new TreeNode(2);
		root.left.right.right = new TreeNode(1);
		root.right = new TreeNode(-3);
		root.right.right = new TreeNode(11);*/
		
		//printDepth();
		//printBreadth();
		//iterativeTraversal();
		//init();
		/*while(hasNext()) {
			System.out.print(next() + " ");
		}*/
		//System.out.println("isBST: " + isBST(root));
		//System.out.println("Max sequence: " + maxSeq());
		//printBreadth();
		//deleteNode(4);
		//deleteNode(3);
		//System.out.println("Tree contains 5: " + contains(5));
		//mirror();
		//printBreadth();
		//System.out.println("Tree is balanced: " + isBalanced());
		//System.out.println("Tree height: " + height(root));
		//getNode(9).right = leftRotation(getNode(9).right);
		//getNode(9).left = leftRightRotation(getNode(9).left);
		//printBreadth();
	}
	
	public void printBoundary() {
		TreeNode n = root;
		//System.out.print(n.val + " ");
		printChildren(root);
		
		while (n != null) {
			if (!(n.left == null && n.right == null))
				System.out.print(n.val + " ");
			if (n.left != null)
				n = n.left;
			else if (n.right != null)
				n = n.right;
			else
				break;
		}
		n = root.right;
		while (n != null) {
			if (!(n.left == null && n.right == null))
				System.out.print(n.val + " ");
			if (n.right != null)
				n = n.right;
			else if (n.left != null)
				n = n.left;
			else break;
		}
	}
	
	private void printChildren(TreeNode n) {
		if (n == null)
			return;
		if (n.left == null && n.right == null)
			System.out.print(n.val + " ");
		printChildren(n.left);
		printChildren(n.right);
	}
	
	public void printPathsWithSum() {
		_printPathsWithSum(root, new TreeNode[size()], 0);
	}
	
	private void _printPathsWithSum(TreeNode n, TreeNode[] path, int len) {
		if (n == null)
			return;
		path[len++] = n;
		if (n.left == null && n.right == null) {
			int sum = 0;
			for (int i = 0; i < len; i++) {
				sum += path[len - i - 1].val * Math.pow(10, i);
			}
			System.out.println(sum);
		} else {
			_printPathsWithSum(n.left, path, len);
			_printPathsWithSum(n.right, path, len);
		}
	}

	public void printPaths(TreeNode n) {
		_printPaths(n, new TreeNode[size()], 0);
	}

	private void _printPaths(TreeNode n, TreeNode[] path, int len) {
		if (n == null)
			return;
		else {
			path[len] = n;
			len++;
		}
		if (n.left == null && n.right == null) {
			for (int i = 0; i < len; i++)
				System.out.print(path[i].val + " ");
			System.out.println();
		} else {
			_printPaths(n.left, path, len);
			_printPaths(n.right, path, len);
		}
	}
	
	public void printTree(TreeNode n) {
		Stack<TreeNode> stack = new Stack<>();
		while (n != null) {
			stack.push(n);
			n = n.left;
		}
		while (!stack.isEmpty()) {
			n = stack.pop();
			System.out.print(n + " ");
			n = n.right;
			while (n != null) {
				stack.push(n);
				n = n.left;
			}
		}
	}
	
	public TreeNode lca(int val1, int val2) {
		TreeNode m, n;
		m = getNode(val1);
		n = getNode(val2);
		List<TreeNode> pathM = new ArrayList<>();
		List<TreeNode> pathN = new ArrayList<>();
		TreeNode parentM = m, parentN = n;
		
		while (parentM.parent != null) {
			pathM.add(0, parentM);
			parentM = parentM.parent;
		}
		while (parentN.parent != null) {
			pathN.add(0, parentN);
			parentN = parentN.parent;
		}
		
		for (int i = 0; i < Math.min(pathM.size(), pathN.size()) - 1; i++) {
			if (!pathM.get(i + 1).equals(pathN.get(i + 1)))
				return pathM.get(i);
		}
		return null;
	}
	
	public void iterativeTraversal() {
		List<Integer> list = new ArrayList<>();
		TreeNode node = root;
		Stack<TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || node != null) {
			while(node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			//System.out.print(node.data + " ");
			list.add(node.val);
			node = node.right;
		}
		System.out.println();
	}
	
	private HashMap<Integer, Integer> map = new HashMap<>();
    
    public int[] findFrequentTreeSum(TreeNode root) {
        trav(root);
        
        int i = 0, max = Integer.MIN_VALUE;
        for (Integer v : map.values()) {
            if (v > max)
            	max = v;
        }
        
        for (Integer v : map.values()) {
        	if (v == max)
        		i++;
        }
        int[] arr = new int[i];
        i = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
        	if (en.getValue() == max)
        		arr[i++] = en.getKey();
        }
        
        return arr;
    }
    
    private int trav(TreeNode node) {
        if (node == null)
            return 0;
        int left = 0, right = 0;
        if (node.left != null) 
        	left =  trav(node.left);
        if (node.right != null)
        	right =  trav(node.right);
        int sum = left + right + node.val;
        map.put(left + right + node.val, map.get(sum) == null ? 1 : map.get(sum) + 1);
        
        return left + right + node.val;
    }
	
	public int maxSeq() {
		int max = 0, cur = 1;
		TreeNode n;
		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> prev = new Stack<>();
		stack.push(root);
		prev.push(root.val);
		while (!stack.isEmpty()) {
			n = stack.pop();
			System.out.print(n.val + " ");
			if (Math.abs(n.val - prev.pop()) == 1) {
				cur++;
				if (cur > max)
					max = cur;
			} else {
				cur = 1;
			}
				
			if (n.right != null) {
				stack.push(n.right);
				prev.push(n.val);
			}
			if (n.left != null) {
				stack.push(n.left);
				prev.push(n.val);
			}
		}
		System.out.println();
		return max;
	}
	
	/*public static class TreeNode {
		public TreeNode left, right, parent;
		public int val;
		
		public TreeNode() {}
		public TreeNode(int val) {
			this.val = val;
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
			return val == ((TreeNode)o).val;
		}
	}*/
	
	public boolean isBST(TreeNode node) {
		if (node == null)
			return true;
		if (node.left != null && node.left.val >= node.val)
			return false;
		if (node.right != null && node.right.val <= node.val)
			return false;
		return isBST(node.left) && isBST(node.right);
	}

	@Override
	public void addNode(int data) {
		if (root == null) {
			root = createNode(data);
			return;
		}
		addNode(data, root);
	}

	private TreeNode addNode(int data, TreeNode node) {
		if (node == null)
			return createNode(data);
		
		if (node.val == data)
			throw new IllegalStateException("Cannot have duplicate data in a BST");

		if (data < node.val) {
			node.left = addNode(data, node.left);
			node.left.parent = node;
		}
		else {
			node.right = addNode(data, node.right);
			node.right.parent = node;
		}
		return node;
	}

	private TreeNode createNode(int data) {
		TreeNode node = new TreeNode();
		node.val = data;
		node.left = null;
		node.right = null;
		return node;
	}

	@Override
	public void deleteNode(int data) {
		deleteNode(data, root);
	}

	private TreeNode deleteNode(int data, TreeNode node) {
		if (node == null)
			return null;
		if (node.val == data) {
			// no children
			if (node.left == null && node.right == null) {
				return null;
			}
			// one child
			if (node.left == null) {
				return node.right;
			}
			if (node.right == null) {
				return node.left;
			}
			// two children
			TreeNode n = smallestNode(node.right);
			node.val = n.val;
			node.right = deleteNode(n.val, node.right);
		} else {
			if (data < node.val)
				node.left = deleteNode(data, node.left);
			if (data > node.val)
				node.right = deleteNode(data, node.right);
		}
		return node;
	}

	private TreeNode smallestNode(TreeNode node) {
		if (node.left == null)
			return node;
		return smallestNode(node.left);
	}

	@Override
	public boolean contains(int data) {
		if (root == null)
			return false;
		return contains(data, root);
	}

	private boolean contains(int data, TreeNode node) {
		if (node == null)
			return false;
		if (data == node.val)
			return true;
		if (data < node.val)
			return contains(data, node.left);
		else
			return contains(data, node.right);
	}
	
	@Override
	public TreeNode getNode(int data) {
		return getNode(data, root);
	}
	
	private TreeNode getNode(int data, TreeNode node) {
		if (node == null)
			return null;
		if (data == node.val)
			return node;
		if (data < node.val)
			return getNode(data, node.left);
		else
			return getNode(data, node.right);
	}

	@Override
	public void mirror() {
		mirror(root);
	}

	private void mirror(TreeNode node) {
		if (node == null)
			return;
		TreeNode tmp = node.left;
		node.left = node.right;
		node.right = tmp;

		mirror(node.left);
		mirror(node.right);
	}

	@Override
	public void printDepth() {
		printDepth(root);
		System.out.println();
	}

	private void printDepth(TreeNode node) {
		if (node == null)
			return;
		printDepth(node.left);
		System.out.print(node.val + " ");
		printDepth(node.right);
	}

	@Override
	public void printBreadth(TreeNode root) throws InterruptedException {
		if (root == null)
			return;
		TreeNode node;
		ArrayBlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(10);
		queue.add(root);
		while(!queue.isEmpty()) {
			node = queue.take();
			System.out.print(node.val + " ");
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);
		}
		System.out.println();
	}

	@Override
	public boolean isBalanced() {
		if (root == null)
			throw new IllegalStateException("A non-existant tree cannot be balanced nor unbalanced");
		int lh, rh;

		lh = height(root.left);
		rh = height(root.right);
		if (Math.abs(lh - rh) <= 1)
			return true;
		return false;
	}
	
	public int size() {
		ArrayBlockingQueue<TreeNode> q = new ArrayBlockingQueue<>(10);
		TreeNode n;
		int size = 0;
		q.add(root);
		while (!q.isEmpty()) {
			n = q.poll();
			size++;
			if (n.left != null)
				q.add(n.left);
			if (n.right != null)
				q.add(n.right);
		}
		return size;
	}

	private int height(TreeNode node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

/*
	A               B
	 \             / \
	  B     ->    A   C
	   \
	    C

	node is A
	caller would write parentOfA.child = leftRotation(A);
*/
	@Override
	public TreeNode leftRotation(TreeNode node) {
		TreeNode b = node.right;
		b.left = node;
		node.right = null;
		return b;
	}

	@Override
	public TreeNode rightRotation(TreeNode node) {
		TreeNode b = node.left;
		b.right = node;
		node.left = null;
		return b;
	}

/*
		C          C         B
	   /          /         / \
	  A    ->    B    ->   A   C
	   \        /
	    B      A

	node is C
*/
	@Override
	public TreeNode leftRightRotation(TreeNode node) {
		// first rotation
		TreeNode a = node.left;
		node.left = a.right;
		node.left.left = a;
		a.right = null;
		
		// second rotation
		return rightRotation(node);
	}

	@Override
	public TreeNode rightLeftRotation(TreeNode node) {
		// first rotation
		TreeNode a = node.right;
		node.right = a.left;
		node.right.right = a;
		a.left = null;

		// second rotation
		return leftRotation(node);
	}
	
	private Stack<TreeNode> stack = new Stack<>();
	
	public void init() {
		TreeNode node = root;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public Integer next() {
		TreeNode node = stack.pop();
		int res = node.val;
		node = node.right;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
		
		return res;
	}

}
