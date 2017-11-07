package com.msi;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;

public class MySorts {

	public void doStuff() throws Exception {
		/*Stack<Integer> stack = new Stack<>();
		stack.push(4);
		stack.push(9);
		stack.push(5);
		stack.push(7);
		stack.push(1);
		stack.push(0);
		stack.push(2);
		stack.push(8);
		stack.push(3);
		stack.push(6);
		stackSort(stack);
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");*/
		/*Integer[] arr = {4, 9, 5, 7, 1, 0, 2, 8, 3, 6};
		bubbleSort(arr);
		printArray(arr);
		arr = new int[]{4, 9, 5, 7, 1, 0, 2, 8, 3, 6};
		insertionSort(arr);
		printArray(arr);
		arr = new int[]{4, 9, 5, 7, 1, 0, 2, 8, 3, 6};
		selectionSort(arr);
		printArray(arr);
		arr = new int[]{4, 9, 5, 7, 1, 0, 2, 8, 3, 6};
		quickSort(arr);
		printArray(arr);*/
		Integer[] arr = new Integer[0];
		ArrayList<Method> sorts = new ArrayList<>();
		sorts.add(getClass().getDeclaredMethod("bubbleSort", arr.getClass()));
		sorts.add(getClass().getDeclaredMethod("insertionSort", Integer[].class));
		sorts.add(getClass().getDeclaredMethod("selectionSort", Integer[].class));
		sorts.add(getClass().getDeclaredMethod("quickSort", Integer[].class));
		for (int i = 0; i < sorts.size(); i++) {
			arr = Misc.betterUniqueRandom(10, 10).toArray(new Integer[0]);
			System.out.println(sorts.get(i).getName());
			sorts.get(i).invoke(this, new Object[]{arr});
			printArray(arr);
//			zigZagSort(arr);
//			boolean dir = false;
//			for (int j = 0; j < arr.length - 1; j++) {
//				if (dir) {
//					if (arr[j] > arr[j + 1])
//						throw new IllegalStateException("invalid sort");
//				} else
//					if (arr[j] < arr[j + 1])
//						throw new IllegalStateException("invalid sort");
//				dir = !dir;
//			}
		}
	}
	
	private void bubbleSort(Integer[] arr) {
		int tmp;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}

	private void insertionSort(Integer[] arr) {
		int tmp;
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j-1]) {
					tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}
	
	private void selectionSort(Integer[] arr) {
		int tmp, min, minIndex = 0;
		
		for (int i = 0; i < arr.length; i++) {
			min = Integer.MAX_VALUE;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < min) {
					min = arr[j];
					minIndex = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = tmp;
		}
	}
	
	private void quickSort(Integer[] arr) {
		qs(arr, 0, arr.length - 1);
	}
	
	private void qs(Integer[] arr, int min, int max) {
		
	}
	
	private int partition(Integer[] arr, int min, int max) {
		return 0;
	}
	
	private void zigZagSort(Integer[] arr) {
		boolean dir = false;
		int tmp;
		for (int j = 0; j < arr.length - 1; j++) {
			if (dir) { // greater than
				if (arr[j] > arr[j + 1]) {
					tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			} else if (arr[j] < arr[j + 1]) {
				tmp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = tmp;
			}
			dir = !dir;
		}
	}

	private void stackSort(Stack<Integer> stack) {
		if (!stack.isEmpty()) {
			int n = stack.pop();
			stackSort(stack);
			insert(stack, n);
		}
	}
	
	private void insert(Stack<Integer> stack, int n) {
		if (stack.isEmpty()) {
			stack.push(n);
			return;
		}
		if (stack.peek() < n) {
			int i = stack.pop();
			insert(stack, n);
			stack.push(i);
		} else
			stack.push(n);
	}
	
	private void printArray(Integer[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
