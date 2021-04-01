package graph;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Queue;
import java.util.LinkedList;

public class DenseGraph {
	
	int[] vertice;
	int[][] matrix;

	/**
	 * creates an empty graph on n nodes
	 * the "names" of the vertices are 0,1,..,n-1 
	 * @param n - number of vertices in the graph
	 */
	public DenseGraph(int n) {
		// TODO Auto-generated constructor stub
		this.vertice = new int[n];
		for (int i = 0; i < n; i++)
			this.vertice[i] = i;
		
		this.matrix = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				this.matrix[i][j] = 0;
		
	}


	/**
	 * @return the adjacency matrix representing the graph
	 */
	public int[][] getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		int length = this.vertice.length;
		
		int[][] temp = new int[length][length];
		for (int i = 0; i < length; i++)
			for (int j = 0; j < length; j++)
				temp[i][j] = this.matrix[i][j];
		
		return temp;
	}

	/**
	 * adds the edge (i,j) to the graph  
	 * no effect if i and j were already adjacent
	 * @param i, j - vertices in the graph
	 */
	public void addEdge(int i, int j) {
		// TODO Auto-generated method stub
		this.matrix[i][j] = 1;
		this.matrix[j][i] = 1;
	}

	/**
	 * removes the edge (i,j) from the graph
	 * no effect if i and j were not adjacent
	 * @param i, j - vertices in the graph
	 */
	public void removeEdge(int i, int j) {
		// TODO Auto-generated method stub
		this.matrix[i][j] = 0;
		this.matrix[j][i] = 0;
	}

	/**
	 * @param i, j - vertices in the graph
	 * @return true if (i,j) is an edge in the graph, and false otherwise
	 */
	public boolean areAdjacent(int i, int j) {
		// TODO Auto-generated method stub
		return (this.matrix[i][j] == 1 && this.matrix[j][i] == 1);
	}

	/**
	 * @param i - a vertex in the graph
	 * @return the degree of i
	 */
	public int degree(int i) {
		// TODO Auto-generated method stub
		int count = 0;
		int length = this.vertice.length;
		
		for (int j = 0; j < length; j++)
			if (this.matrix[i][j] == 1)
				count++;
		
		return count;
	}
	
	/**
	 * The iterator must output the neighbors of i in the increasing order
	 * Assumption: the graph is not modified during the use of the iterator 
	 * @param i - a vertex in the graph
	 * @return an iterator that returns the neighbors of i
	 */
	public Iterator<Integer> neighboursIterator(int i) {
		// TODO Auto-generated method stub
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int length = this.vertice.length;
		
		for (int j = 0; j < length; j++) {
			if (this.areAdjacent(i, j)) {
				Integer add = Integer.valueOf(j);
				temp.add(add);
			}	
		}
		
		return temp.iterator();
	}

	/**
	 * @return number of vertices in the graph
	 */
	public int numberOfVertices() {
		// TODO Auto-generated method stub
		return this.vertice.length;
	}

	/**
	 * @return number of edges in the graph
	 */
	public int numberOfEdges() {
		// TODO Auto-generated method stub
		int count = 0;
		int length = this.vertice.length;
		
		for (int i = 0; i < length; i++)
			for (int j = i; j < length; j++)
				if (this.areAdjacent(i, j))
					count++;
		
		return count;
	}

	/**
	 * @param i, j - vertices in the graph
	 * @return distance between i and j in the graph
	 */
	public int distance(int i, int j) {
		// TODO Auto-generated method stub
		if (this.areAdjacent(i, j))
			return 1;
		
		
		int length = this.vertice.length;
		
		
		
		Vector<Boolean> visited = new Vector<Boolean>(length);
		for (int x = 0; x < length; x++)
			visited.addElement(false);
		
		Vector<Integer> dist = new Vector<Integer>(length);
		for (int x = 0; x < length; x++)
			dist.addElement(0);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		dist.setElementAt(0, i);
		queue.add(i);
		visited.setElementAt(true, i);
		
		while(queue.isEmpty() == false) {
			int parent = queue.peek();
			queue.poll();
			for (int x = 0; x < length; x++) {
				if (this.areAdjacent(x, parent)) {
					if (visited.elementAt(x))
						continue;
				
					dist.setElementAt(dist.get(parent) + 1, x);
					queue.add(x);
					visited.setElementAt(true, x);
				}
			}
		}
		
		return dist.get(j);
	}

	/**
	 * @param n - number of vertices
	 * @param p - number between 0 and 1
	 * @return a random graph on n vertices, where each edge is added to the graph with probability p
	 */
	public static DenseGraph generateRandomGraph(int n, double p) {
		// TODO Auto-generated method stub
		DenseGraph temp = new DenseGraph(n);
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				double prob = Math.random();
				if (prob <= p) {
					temp.addEdge(i, j);
				}		
			}
		}
		
		return temp;
	}

}