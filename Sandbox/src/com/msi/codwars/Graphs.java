package com.msi.codwars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graphs {
	
	public static void doStuff() {
		Graphs g = new Graphs();
		GraphTest gt = g.new GraphTest();
		gt.setUpSmallExampleGraph();
		hopDistance(gt.exampleGraph, gt.V[2], gt.V[5]);
	}
	
	public static int departmentConnections(Graph graph, Set<Vertex> d1, Set<Vertex> d2){
        return 0;
    }
	
	public static int hopDistance(Graph graph, Vertex source, Vertex target){
		HashSet<Vertex> visited = new HashSet<>();
		HashMap<Vertex, Integer> dist = new HashMap<>();
		if (!graph.vertices.contains(source) || !graph.vertices.contains(target))
			return -1;
		
		for (Vertex v : graph.vertices) {
			dist.put(v, Integer.MAX_VALUE);
		}
		dist.put(source, 0);
		
		while (!visited.contains(target)) {
			int min = Integer.MAX_VALUE;
			Vertex minVertex = null;
			// find the min vertex
			for (Map.Entry<Vertex, Integer> en : dist.entrySet()) {
				if (en.getValue() < min && !visited.contains(en.getKey())) {
					min = en.getValue();
					minVertex = en.getKey();
				}
			}
			if (minVertex == null)
				return -1;
			dist.put(minVertex, min);
			visited.add(minVertex);
			
			// update its neighbors
			for (Vertex v : getNeighbours(graph, minVertex)) {
				if (!visited.contains(v)
						&& dist.get(minVertex) + 1 < dist.get(v))
					dist.put(v, dist.get(minVertex) + 1);
			}
			
		}
		return dist.get(target);
    }
	
	public static Set<Vertex> getNeighbours(Graph graph, Vertex vertex){
		Set<Vertex> vertices = new HashSet<>();
		for (Edge e : graph.edges) {
			if (e.v1.equals(vertex))
				vertices.add(e.v2);
			else if (e.v2.equals(vertex))
				vertices.add(e.v1);
		}
		return vertices;
    }
	
	public class GraphTest {
	    Graph exampleGraph;
	    Vertex[] V = new Vertex[]{new Vertex(), new Vertex(), new Vertex(), new Vertex(), new Vertex(), new Vertex()};

	    public void setUpSmallExampleGraph(){
	        exampleGraph = new Graph();
	        /*
	        *      V[2] - V[0] - V[3] - V[4]    V[5]
	        *              |       |
	        *             V[1] - - -
	        */
	        exampleGraph.addEdges(V[0], V[1], V[0], V[2], V[0], V[3], V[1], V[3], V[3], V[4]);
	        exampleGraph.addVertex(V[5]);
	    }
	}
	
	public static class Graph {
	    private static int uidCounter = 0;
	    Set<Vertex> vertices;
	    Set<Edge> edges;

	    public Graph(){
	        vertices = new HashSet<>();
	        edges = new HashSet<>();
	    }

	    public void addVertex(Vertex vertex){
	        vertices.add(vertex);
	    }

	    public void addVertices(Vertex... vertices){
	        for(Vertex v: vertices)
	            addVertex(v);
	    }

	    public void addEdge(Vertex v1, Vertex v2){
	        addEdge(new Edge(v1, v2));
	    }

	    public void addEdge(Edge edge){
	        vertices.add(edge.v1);
	        vertices.add(edge.v2);
	        edges.add(edge);
	    }

	    public void addEdges(Vertex... vertices){
	        if(vertices.length%2 != 0)
	            throw new IllegalArgumentException();

	        for(int i = 0; i< vertices.length; i=i+2){
	            addEdge(vertices[i], vertices[i+1]);
	        }
	    }

	    public Set<Vertex> getVertices(){ return vertices; }

	    public Set<Edge> getEdges(){ return edges; }

	    static int getUidForNode(){ return uidCounter++; }
	}
	
	static class Vertex {
	    private final int uid;

	    Vertex(){
	        uid = Graph.getUidForNode();
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Vertex other = (Vertex) o;
	        return uid == other.uid;
	    }

	    @Override
	    public int hashCode() { return uid; }
	}
	
	static class Edge {
	    Vertex v1;
	    Vertex v2;

	    Edge(Vertex v1, Vertex v2){
	        this.v1 = v1;
	        this.v2 = v2;
	    }

	    public Vertex getV1() { return v1; }

	    public Vertex getV2() { return v2; }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Edge other = (Edge) o;
	        return (v1.equals(other.v1) && v2.equals(other.v2)) || (v1.equals(other.v2) && v2.equals(other.v1));
	    }

	    @Override
	    public int hashCode() {
	        return v1.hashCode() + v2.hashCode();
	    }
	}
}
