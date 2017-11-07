package com.msi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyLinkedList {
	
	private ListNode root;

	public MyLinkedList() {
		//for (int i = 0; i < 10; i++)
		//	add(i);
		add(9);
		add(5);
		add(4);
		add(8);
		add(1);
		add(3);
		add(6);
		add(2);
		add(7);
		//printList(root);
		/*add(1);
		add(2);
		add(3);
		add(4);
		add(5);
		add(6);
		add(7);
		add(8);
		List<Integer> list = Misc.betterUniqueRandom(length(), length());
		for (int i = 0; i < length(); i++) {
			get(i).rand = get(list.remove(0));
		}*/
	}
	
	public void doStuff() {
		//System.out.println("Is palindrome: " + betterPalindrome());
		//printList();
		//ListNode n = deepCopy(root);
		//sort();
		//_newReverse(root, null);
		newReverseIterative();
		printList();
		//printList(n);
		//root = simpleRev();
		//root = reverseK(3);
		//recursiveReverse();
		//iterativeReverse();
		//printList();
		//oddEvenList(root);
		//root = rotateRight(root, 2);
		//printList();
	}
	
	private void newReverseIterative() {
		ListNode cur = root, prev = null, tmp;
		
		while (cur != null) {
			tmp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = tmp;
		}
		root = prev;
	}
	
	private void _newReverse(ListNode cur, ListNode prev) {
		if (cur == null) {
			root = prev;
			return;
		}
		_newReverse(cur.next, cur);
		cur.next = prev;
	}
	
	public void sort() {
		ListNode prev, cur, next;
		boolean isRoot;
		
		for (int i = 0; i < length(); i++) {
			isRoot = true;
			prev = null; cur = root; next = cur.next;
			while (next != null) {
				if (cur.value > next.value) {
					if (isRoot) {
						root = next;
						isRoot = false;
					}
					if (prev != null)
						prev.next = next;
					cur.next = next.next;
					next.next = cur;
					prev = next;
					next = cur.next;
				} else {
					prev = cur;
					cur = next;
					next = next.next;
					isRoot = false;
				}
				printList();
			}
		}
	}
	
	public ListNode deepCopy(ListNode root) {
		HashMap<ListNode, ListNode> map = new HashMap<>();
		ListNode n = new ListNode(root.value);
		ListNode copy = n;
		ListNode ptr = root, prev = null;
		map.put(n, ptr.rand);
		while (ptr != null) {
			if (ptr.next != null)
				n.next = new ListNode(ptr.next.value);
			map.put(ptr, ptr.next);
			n.rand = ptr;
			prev = ptr;
			ptr = ptr.next;
			prev.next = n;
			n = n.next;
		}
		n = copy;
		while (n != null) {
			ptr = n.rand.rand;
			n.rand = n.rand.rand.next;
			ptr.next = map.get(ptr);
			n = n.next;
		}
		
		return copy;
	}
	
	public ListNode simpleRev() {
		ListNode cur = root, prev = null, next = cur != null ? cur.next : null;
		
		while (cur != null) {
			cur.next = prev;
			prev = cur;
			cur = next;
			if (next != null)
				next = next.next;
		}
		return prev;
	}
	
	// 1->2->3->4->5->6
	// 3->2->1->6->5->4
	public ListNode reverseK(int k) {
		ListNode cur = root, prev = null, next = cur != null ? cur.next : null;
		List<ListNode> list = new ArrayList<>();
		
		for (int j = 0; j < k; j++) {
			for (int i = 0; i < k && cur != null; i++) {
				cur.next = prev;
				prev = cur;
				cur = next;
				if (next != null)
					next = next.next;
			}
			list.add(prev);
			prev = null;
		}
		for (int i = 0; i < list.size() - 1; i++) {
			cur = list.get(i);
			while (cur.next != null)
				cur = cur.next;
			cur.next = list.get(i + 1);
		}
		
		return list.get(0);
	}
	
	public ListNode rotateRight(ListNode head, int k) {
		k %= length();
		if (head == null || head.next == null || k <= 0)
			return head;
        ListNode kthNode = head, ptr = head, prev = head;
        ListNode newHead;
        
        for (int i = 0; ptr != null; i++) {
        	ptr = ptr.next;
        	if (i >= k)
        		kthNode = kthNode.next;
        	if (i > k)
        		prev = prev.next;
        }
        newHead = kthNode;
        prev.next = null;
        while (kthNode.next != null)
        	kthNode = kthNode.next;
        kthNode.next = head;
        return newHead;
    }
	
	public ListNode oddEvenList(ListNode head) {
        ListNode even = head != null ? head.next : null;
        ListNode n = even != null ? even.next : null;
        ListNode evenHead = even;
        ListNode odd = head;
        if (n == null)
        	return head;
        int i = 3;
        while (n != null) {
        	if (i % 2 == 1) {
        		odd.next = n;
        		odd = n;
        	} else {
        		even.next = n;
        		even = n;
        	}
        	i++;
        	n = n.next;
        }
        odd.next = evenHead;
        even.next = null;
        return head;
    }
	
	private boolean isPalindrome() {
		int len = length();
		ListNode n = root;
		for (int i = 0; i < len / 2; i++) {
			if (n.value != get(len - i - 1).value)
				return false;
			n = n.next;
		}
		return true;
	}
	
	private boolean betterPalindrome() {
		MyStack stack = new MyStack();
		int len = length();
		ListNode n = len % 2 == 0 ? get(len / 2) : get(len / 2 + 1); // O(n)
		while(n != null) { // O(n)
			stack.push(n.value);
			n = n.next;
		}
		n = root;
		for (int i = 0; i < len / 2; i++) {
			if (n.value != stack.pop().data)
				return false;
			n = n.next;
		}
		// O(3n/2) = O(n)
		return true;
	}
	
	private ListNode get(int pos) {
		int i = 0;
		ListNode n = root;
		
		while (i != pos && n != null) {
			i++;
			n = n.next;
		}
		return n;
	}
	
	private int length() {
		if (root == null)
			return 0;
		int len = 0;
		ListNode n = root;
		while (n != null) {
			len++;
			n = n.next;
		}
		return len;
	}
	
	public void iterativeReverse() {
		ListNode prev = null, cur = root, next = root.next;
		
		while (cur != null) {
			cur.next = prev;
			prev = cur;
			cur = next;
			if (next != null)
				next = next.next;
		}
		root = prev;
	}
	
	public void recursiveReverse() {
		rev(root, null);
	}
	
	private void rev(ListNode cur, ListNode prev) {
		if (cur == null) {
			root = prev;
			return;
		}
		rev(cur.next, cur);
		cur.next = prev;
	}
	
	public void reverse() {
		List<ListNode> list = new ArrayList<>();
		ListNode ptr = root;
		while (ptr != null) {
			list.add(ptr);
			ptr = ptr.next;
		}
		root = list.get(list.size() - 1);
		for (int i = list.size() - 1; i > 0; i--)
			list.get(i).next = list.get(i - 1);
		list.get(0).next = null;
	}
	
	private ListNode getLast() {
		ListNode ptr = root;
		while (ptr.next != null)
			ptr = ptr.next;
		return ptr;
	}
	
	public void add(int value) {
		if (root == null) {
			root = new ListNode(value);
			return;
		}
		ListNode ptr = root;
		ListNode n = new ListNode(value);
		while (ptr.next != null)
			ptr = ptr.next;
		ptr.next = n;
	}
	
	public void printList() {
		ListNode ptr = root;
		while (ptr != null) {
			//System.out.print("(" + ptr.value + ", " + ptr.rand.value + ") ");
			System.out.print(ptr.value + " ");
			ptr = ptr.next;
		}
		System.out.println();
	}
	
	public void printList(ListNode root) {
		ListNode ptr = root;
		while (ptr != null) {
			System.out.print(ptr.value + " ");
			ptr = ptr.next;
		}
		System.out.println();
	}
	
	private static class ListNode {
		private ListNode(int val) {
			value = val;
		}
		private ListNode next, rand;
		private int value, carry;
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof ListNode))
				return false;
			return value == ((ListNode)o).value;
		}
	}

}
