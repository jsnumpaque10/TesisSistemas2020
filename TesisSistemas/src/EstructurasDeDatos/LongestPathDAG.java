package EstructurasDeDatos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;

/* Computes the longest path between the last vertex and any other vertex in a DAG */

public class LongestPathDAG {
	
	Digraph G;
	private Topological topologicalOrder;
	List <Integer> topologicalOrderList;
	
	/*
	 * Constructor for the class
	 * vertex1 and vertex2 are vertices in the digraph G 
	 */
	public LongestPathDAG(Digraph pG){
		
		G=pG;
		
		topologicalOrder = new Topological(G);
		Iterable <Integer> topologicalOrderIterable = topologicalOrder.order();
		Iterator <Integer> iteratorTopologicalOrder = topologicalOrderIterable.iterator();
		
		// Save the elements of topological order in a List
		topologicalOrderList = new ArrayList<>();
		iteratorTopologicalOrder.forEachRemaining(topologicalOrderList::add);
		
		// Prints the elements of the topological order of the graph
		System.out.print("The topological order of the graph is:");
		for (int i = 0; i<topologicalOrderList.size(); i++)
		{	
			System.out.print(" "+topologicalOrderList.get(i).toString());
		}
		System.out.println(".");

		
	}
	
	/**
	 * Calculate the longest path from a given vertex to the last vertex in the graph
	 * @param vertex
	 * @return the longest path to the given vertex
	 */
	public int calculateLongestPathLengthFromVertex (int vertex)
	{
		int longestPathLengthFromVertex = 0;
		int currentVertexLongestPath=0;
		Iterable <Integer> adjacentVertices = G.adj(vertex);
		Iterator <Integer> adjacentVerticesIterator = adjacentVertices.iterator();
		if(adjacentVerticesIterator.hasNext())
		{
			while(adjacentVerticesIterator.hasNext())
			{	
				Integer currentVertex = adjacentVerticesIterator.next().intValue();
				currentVertexLongestPath = calculateLongestPathLengthFromVertex(currentVertex)+1; 
				if (currentVertexLongestPath > longestPathLengthFromVertex)
				{
					longestPathLengthFromVertex = currentVertexLongestPath;
				}
			}
		}
		return longestPathLengthFromVertex;
	}
	
}