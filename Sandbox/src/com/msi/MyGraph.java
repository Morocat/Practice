package com.msi;

import java.util.*;

public class MyGraph {

	public void doStuff() {
		//shortestPath();
		//minSpanningTree();
		dijkstras(graph, 0, 4);
	}
	
	void dijkstras(int[][] graph, int start, int end) {
		Set<Integer> set = new HashSet<>();
		int[] dist = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		
		for (int i = 0; i < graph.length; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
		
		while (!set.contains(end)) {
			int min = findMinVertex(visited, dist);
			set.add(min);
			visited[min] = true;
			
			for (int i = 0; i < graph.length; i++) {
				if (graph[min][i] > 0
					&& !visited[i]
					&& dist[min] + graph[min][i] < dist[i]) {
					dist[i] = dist[min] + graph[min][i];
				}
			}
		}
		System.out.println();
	}
	
	int findMinVertex(boolean[] visited, int[] dist) {
		int min = Integer.MAX_VALUE, min_index = 0;
		for (int i = 0; i < graph.length; i++) {
			if (!visited[i] && dist[i] < min) {
				min = dist[i];
				min_index = i;
			}
		}
		return min_index;
	}
	
	void minTree() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private final int graph[][] = {
			{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
			{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
			{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
			{ 0, 0, 7, 0, 9, 14, 0, 0, 0 },
			{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
			{ 0, 0, 4, 14, 10, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 2, 0, 1, 6 },
			{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
			{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }
		};
	
	void minSpanningTree() {

		// nodes included in the mst. ith index connects to mst[i]
		int[] mst = new int[graph.length];

		// list of edges in the MST
		int[] edges = new int[graph.length];

		// whether the vertex has been chosen yet
		// index = vertex
		boolean[] chosen = new boolean[graph.length];

		for (int i = 0; i < graph.length; i++) {
			edges[i] = Integer.MAX_VALUE;
			//chosen[i] = false; // java already does this
		}
		edges[0] = 0; // choose the first vertex

		for (int i = 0; i < graph.length - 1; i++) {
			int min = findMin(edges, chosen);
			chosen[min] = true;

			for (int j = 0; j < graph.length; j++) {
				if (graph[min][j] > 0 // if an edge exists
					&& !chosen[j] // and it hasn't been chosen yet
					&& graph[min][j] < edges[j]) {
					mst[j] = min;
					edges[j] = graph[min][j];
				}
			}
		}
		for (int i = 0; i < mst.length; i++)
			System.out.println(i + " -> " + mst[i]);
	}

	// returns the index of the next node to pick based on it having the minimum edge weight
	int findMin(int[] edges, boolean[] chosen) {
		int min_edge = Integer.MAX_VALUE, min_index = -1;

		for (int i = 0; i < edges.length; i++) {
			if (edges[i] < min_edge && !chosen[i]) {
				min_edge = edges[i];
				min_index = i;
			}
		}
		return min_index;
	}
	
}
