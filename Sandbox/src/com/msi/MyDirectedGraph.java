package com.msi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class MyDirectedGraph {
	
	private ArrayList<Integer> adj[] = new ArrayList[6];
	
	public MyDirectedGraph() {
		for (int i = 0; i < 6; i++)
			adj[i] = new ArrayList<Integer>();
		
		addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(3, 3);
		addEdge(5, 2);
        addEdge(5, 0);
        addEdge(4, 0);
        addEdge(4, 1);
        addEdge(2, 3);
        addEdge(3, 1);
	}
	
	public void doStuff() {
		breadthTraversal(2);
		depthFirstTraversal(2, new boolean[6]);
		//topologicalSort();
	}
	
	private void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public void breadthTraversal(int start) {
		boolean[] visited = new boolean[adj.length];
		LinkedList<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(start);
		while (!queue.isEmpty()) {
			int n = queue.remove();
			System.out.print(n + " ");
			for (int i : adj[n]) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		System.out.println();
	}
	
	void depthFirstTraversal(int vertex, boolean[] visited) {
		visited[vertex] = true;
		System.out.print(vertex + " ");
		for (int i : adj[vertex]) {
			if (!visited[i])
				depthFirstTraversal(i, visited);
		}
	}
	
	void topologicalSort() {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[6];
		
		for (int i = 0; i < 6; i++) {
			if (!visited[i])
				topoSort(i, visited, stack);
		}
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");
	}
	
	void topoSort(int vertex, boolean[] visited, Stack<Integer> stack) {
		visited[vertex] = true;
		for (int i : adj[vertex]) {
			if (!visited[i])
				topoSort(i, visited, stack);
		}
		stack.push(vertex);
	}
}
