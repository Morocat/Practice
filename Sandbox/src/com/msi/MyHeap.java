package com.msi;

import java.util.Random;

/**
 * Minimum heap
 * @author alelop
 */
public class MyHeap {
	private final static Random rand = new Random();
	
	private HeapNode[] heap = new HeapNode[10];
	private int size = 0;
	
	public void doStuff() {
		while (!Thread.interrupted()) {
			add(rand.nextInt(50), RandomObject.next());
			add(rand.nextInt(50), RandomObject.next());
			add(rand.nextInt(50), RandomObject.next());
			add(rand.nextInt(50), RandomObject.next());
			add(rand.nextInt(50), RandomObject.next());
			add(rand.nextInt(50), RandomObject.next());
			add(rand.nextInt(50), RandomObject.next());
			while (!isEmpty())
				System.out.println(remove().toString());
		}
	}
	
	// O(n) is O(Log2(n)) worst case, O(1) best
	public void add(int key, Object data) {
		if (size == heap.length)
			throw new IllegalStateException("Heap is full!");
		HeapNode n = new HeapNode(key, data);
		int index = size++;
		heap[index] = n;
		// sift up if necessary
		while (parent(index).key > n.key) {
			HeapNode tmp = heap[index];
			heap[index] = parent(index);
			index = (index - 1) / 2;
			heap[index] = tmp;
		}
	}
	
	public HeapNode remove() {
		if (isEmpty())
			return null;
		HeapNode ret = heap[0], tmp;
		size--;
		heap[0] = heap[size];
		heap[size] = null;
		int index = 0;
		int child = 2;
		if (heap[child] == null)
			child = 1;
		if (heap[child] == null)
			return ret;
		//int child = heap[1].key > heap[2].key ? 2 : 1;
		while(child < heap.length && heap[child] != null) {
			// swap index and child
			tmp = heap[index];
			heap[index] = heap[child];
			heap[child] = tmp;
			index = child;
			child = index * 2 + 2; // assume smallest child is the right child
			
			if (child >= heap.length) { // if this position doesn't exist in the heap
				child = index * 2 + 1; // assume left child
				continue;
			}
			if (child >= heap.length) // if this position still doesn't exist
				return ret; // we're done

			// at this point both child positions are guaranteed to exist
			if (heap[child] == null) { // if right child doesn't exist
				if (heap[child - 1] == null) // check if left child exists
					return ret; // if not we're done
				child--; // otherwise we pick the left child
				continue;
			}
			// otherwise pick the smallest of the two children
			// at this point child is the right child
			child = heap[child].key < heap[child - 1].key ? child : child - 1;
		}
		return ret;
	}
	
	private HeapNode leftChild(int index) {
		return heap[index * 2 + 1];
	}
	
	private HeapNode rightChild(int index) {
		return heap[index * 2 + 2];
	}
	
	private HeapNode parent(int index) {
		return heap[(index - 1) / 2];
	}
	
	public int height() {
		return (int)(Math.log(size) / Math.log(2));
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public static class HeapNode {
		int key;
		Object data;
		HeapNode left, right;
		
		public HeapNode(int key, Object data) {
			this.key = key;
			this.data = data;
		}
		
		@Override
		public String toString() {
			return "Key:" + key + "\nData: " + data.toString();
		}
	}

}
