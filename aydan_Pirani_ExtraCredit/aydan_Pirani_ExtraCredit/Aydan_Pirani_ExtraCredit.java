package aydan_Pirani_ExtraCredit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aydan_Pirani_ExtraCredit {

	public static void main (String[] args) throws FileNotFoundException {
		File file1 = new File("File 1");
		File file2 = new File("File 2");
				
		System.out.println("GRAPH 1:");
		if (testConnectedGraph(file1)) {
			System.out.println("Graph 1 is connected.");
		} else {
			System.out.println("Graph 1 is not connected.");
		}
		
		System.out.println();
		System.out.println("GRAPH 2:");
		if (testConnectedGraph(file2)) {
			System.out.println("Graph 2 is connected.");
		} else {
			System.out.println("Graph 2 is not connected.");
		}

	}
	public static boolean testConnectedGraph(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		Integer[] vertices = new Integer[sc.nextInt()];
		int totalEdges = 0;
		sc.nextLine();

		//Create array of vertices and get amount of edges
		for (int i = 0; sc.hasNext(); i++) {
			String tempStr = sc.nextLine();
			String[] tempArr = tempStr.split(" ");
			totalEdges = totalEdges + tempArr.length-1;
			vertices[i] = Integer.parseInt(tempArr[0]);
		} 
		sc.close();
		
		//Create array of edges
		int[][] edges = new int[totalEdges][2];
		sc = new Scanner(file);
		sc.nextLine();
		int repetitions = 0;
		while (sc.hasNext()) {
			String tempStr = sc.nextLine();
			String[] tempArr = tempStr.split(" ");
			for (int j = 0; j < tempArr.length-1; j++) {
				edges[repetitions][0] = Integer.parseInt(tempArr[0]);
				edges[repetitions][1] = Integer.parseInt(tempArr[j+1]);
				repetitions++;
			}
		}
		sc.close();

		UnweightedGraph<Integer> g = new UnweightedGraph<Integer>(vertices, edges);
		g.printEdges();
		if (g.dfs(0).getNumberOfVerticesFound() == vertices.length) {
			return true;
		} else {
			return false;
		}
	}

}

class UnweightedGraph<V> extends AbstractGraph<V> {
	/** Construct an empty graph */
	public UnweightedGraph() {
	}

	/** Construct a graph from vertices and edges stored in arrays */
	public UnweightedGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}

	/** Construct a graph from vertices and edges stored in List */
	public UnweightedGraph(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}

	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	/** Construct a graph from integer vertices 0, 1, and edge array */
	public UnweightedGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}
}


abstract class AbstractGraph<V> implements Graph<V> {
	protected List<V> vertices = new ArrayList<>(); // Store vertices
	protected List<List<Edge>> neighbors = new ArrayList<>(); // Adjacency lists

	/** Construct an empty graph */
	protected AbstractGraph() {
	}

	/** Construct a graph from vertices and edges stored in arrays */
	protected AbstractGraph(V[] vertices, int[][] edges) {
		for (int i = 0; i < vertices.length; i++)
			addVertex(vertices[i]);

		createAdjacencyLists(edges, vertices.length);
	}

	/** Construct a graph from vertices and edges stored in List */
	protected AbstractGraph(List<V> vertices, List<Edge> edges) {
		for (int i = 0; i < vertices.size(); i++)
			addVertex(vertices.get(i));

		createAdjacencyLists(edges, vertices.size());
	}

	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	@SuppressWarnings("unchecked")
	protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
		for (int i = 0; i < numberOfVertices; i++) 
			addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}

		createAdjacencyLists(edges, numberOfVertices);
	}

	/** Construct a graph from integer vertices 0, 1, and edge array */
	@SuppressWarnings("unchecked")
	protected AbstractGraph(int[][] edges, int numberOfVertices) {
		for (int i = 0; i < numberOfVertices; i++) 
			addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}

		createAdjacencyLists(edges, numberOfVertices);
	}

	/** Create adjacency lists for each vertex */
	private void createAdjacencyLists(
			int[][] edges, int numberOfVertices) {
		for (int i = 0; i < edges.length; i++) {
			addEdge(edges[i][0], edges[i][1]);
		}
	}

	/** Create adjacency lists for each vertex */
	private void createAdjacencyLists(
			List<Edge> edges, int numberOfVertices) {
		for (Edge edge: edges) {
			addEdge(edge.u, edge.v);
		}
	}

	@Override /** Return the number of vertices in the graph */
	public int getSize() {
		return vertices.size();
	}

	@Override /** Return the vertices in the graph */
	public List<V> getVertices() {
		return vertices;
	}

	@Override /** Return the object for the specified vertex */
	public V getVertex(int index) {
		return vertices.get(index);
	}

	@Override /** Return the index for the specified vertex object */
	public int getIndex(V v) {
		return vertices.indexOf(v);
	}

	@Override /** Return the neighbors of the specified vertex */
	public List<Integer> getNeighbors(int index) {
		List<Integer> result = new ArrayList<>();
		for (Edge e: neighbors.get(index))
			result.add(e.v);

		return result;
	}

	@Override /** Return the degree for a specified vertex */
	public int getDegree(int v) {
		return neighbors.get(v).size();
	}

	@Override /** Print the edges */
	public void printEdges() {
		for (int u = 0; u < neighbors.size(); u++) {
			System.out.print("Vertex " + getVertex(u) + ": ");
			for (Edge e: neighbors.get(u)) {
				System.out.print("(" + getVertex(e.u) + ", " +
						getVertex(e.v) + ") ");
			}
			System.out.println();
		}
	}

	@Override /** Clear the graph */
	public void clear() {
		vertices.clear();
		neighbors.clear();
	}

	@Override /** Add a vertex to the graph */  
	public boolean addVertex(V vertex) {
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			neighbors.add(new ArrayList<Edge>());
			return true;
		}
		else {
			return false;
		}
	}

	/** Add an edge to the graph */  
	protected boolean addEdge(Edge e) {
		if (e.u < 0 || e.u > getSize() - 1)
			throw new IllegalArgumentException("No such index: " + e.u);

		if (e.v < 0 || e.v > getSize() - 1)
			throw new IllegalArgumentException("No such index: " + e.v);

		if (!neighbors.get(e.u).contains(e)) {
			neighbors.get(e.u).add(e);
			return true;
		}
		else {
			return false;
		}
	}

	@Override /** Add an edge to the graph */  
	public boolean addEdge(int u, int v) {
		return addEdge(new Edge(u, v));
	}

	/** Edge inner class inside the AbstractGraph class */
	public static class Edge {
		public int u; // Starting vertex of the edge
		public int v; // Ending vertex of the edge

		/** Construct an edge for (u, v) */
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}

		public boolean equals(Object o) {
			return u == ((Edge)o).u && v == ((Edge)o).v; 
		}
	}

	@Override /** Obtain a DFS tree starting from vertex v */
	/** To be discussed in Section 28.6 */
	public Tree dfs(int v) {
		List<Integer> searchOrder = new ArrayList<>();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // Initialize parent[i] to -1

		// Mark visited vertices
		boolean[] isVisited = new boolean[vertices.size()];

		// Recursively search
		dfs(v, parent, searchOrder, isVisited);

		// Return a search tree
		return new Tree(v, parent, searchOrder);
	}

	/** Recursive method for DFS search */
	private void dfs(int u, int[] parent, List<Integer> searchOrder,
			boolean[] isVisited) {
		// Store the visited vertex
		searchOrder.add(u);
		isVisited[u] = true; // Vertex v visited

		for (Edge e : neighbors.get(u)) {
			if (!isVisited[e.v]) {
				parent[e.v] = u; // The parent of vertex e.v is u
				dfs(e.v, parent, searchOrder, isVisited); // Recursive search
			}
		}
	}

	@Override /** Starting bfs search from vertex v */
	/** To be discussed in Section 28.7 */
	public Tree bfs(int v) {
		List<Integer> searchOrder = new ArrayList<>();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // Initialize parent[i] to -1

		java.util.LinkedList<Integer> queue =
				new java.util.LinkedList<>(); // list used as a queue
		boolean[] isVisited = new boolean[vertices.size()];
		queue.offer(v); // Enqueue v
		isVisited[v] = true; // Mark it visited

		while (!queue.isEmpty()) {
			int u = queue.poll(); // Dequeue to u
			searchOrder.add(u); // u searched
			for (Edge e: neighbors.get(u)) {
				if (!isVisited[e.v]) {
					queue.offer(e.v); // Enqueue w
					parent[e.v] = u; // The parent of w is u
					isVisited[e.v] = true; // Mark it visited
				}
			}
		}

		return new Tree(v, parent, searchOrder);
	}

	/** Tree inner class inside the AbstractGraph class */
	/** To be discussed in Section 28.5 */
	public class Tree {
		private int root; // The root of the tree
		private int[] parent; // Store the parent of each vertex
		private List<Integer> searchOrder; // Store the search order

		/** Construct a tree with root, parent, and searchOrder */
		public Tree(int root, int[] parent, List<Integer> searchOrder) {
			this.root = root;
			this.parent = parent;
			this.searchOrder = searchOrder;
		}

		/** Return the root of the tree */
		public int getRoot() {
			return root;
		}

		/** Return the parent of vertex v */
		public int getParent(int v) {
			return parent[v];
		}

		/** Return an array representing search order */
		public List<Integer> getSearchOrder() {
			return searchOrder;
		}

		/** Return number of vertices found */
		public int getNumberOfVerticesFound() {
			return searchOrder.size();
		}

		/** Return the path of vertices from a vertex to the root */
		public List<V> getPath(int index) {
			ArrayList<V> path = new ArrayList<>();

			do {
				path.add(vertices.get(index));
				index = parent[index];
			}
			while (index != -1);

			return path;
		}

		/** Print a path from the root to vertex v */
		public void printPath(int index) {
			List<V> path = getPath(index);
			System.out.print("A path from " + vertices.get(root) + " to " +
					vertices.get(index) + ": ");
			for (int i = path.size() - 1; i >= 0; i--)
				System.out.print(path.get(i) + " ");
		}

		/** Print the whole tree */
		public void printTree() {
			System.out.println("Root is: " + vertices.get(root));
			System.out.print("Edges: ");
			for (int i = 0; i < parent.length; i++) {
				if (parent[i] != -1) {
					// Display an edge
					System.out.print("(" + vertices.get(parent[i]) + ", " +
							vertices.get(i) + ") ");
				}
			}
			System.out.println();
		}
	}
}

interface Graph<V> {
	/** Return the number of vertices in the graph */
	public int getSize();

	/** Return the vertices in the graph */
	public java.util.List<V> getVertices();

	/** Return the object for the specified vertex index */
	public V getVertex(int index);

	/** Return the index for the specified vertex object */
	public int getIndex(V v);

	/** Return the neighbors of vertex with the specified index */
	public java.util.List<Integer> getNeighbors(int index);

	/** Return the degree for a specified vertex */
	public int getDegree(int v);

	/** Print the edges */
	public void printEdges();

	/** Clear the graph */
	public void clear();

	/** Add a vertex to the graph */  
	public boolean addVertex(V vertex);

	/** Add an edge to the graph */  
	public boolean addEdge(int u, int v);

	/** Obtain a depth-first search tree */
	public AbstractGraph<V>.Tree dfs(int v);

	/** Obtain a breadth-first search tree */
	public AbstractGraph<V>.Tree bfs(int v);
}