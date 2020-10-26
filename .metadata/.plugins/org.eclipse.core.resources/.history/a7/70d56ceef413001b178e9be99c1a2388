package EstructurasDeDatos;

import edu.princeton.cs.algs4.Digraph;

//Generates a random DAG that has the number of vertices given
public class DagGenerator {
	
	//number of vertices of the DAG
	private int V; 
	
	// Adjacency matrix of the DAG
	private int adjacencyMatrizDAG [][];
	
	
	public DagGenerator (int pV)
	{
		V= pV;
		adjacencyMatrizDAG = new int [V][V];
	}
	
	/**
	 * Generates the adjacency matrix of a DAG with V vertices
	 * @return The adjacency matrix of the DAG
	 */
	private int[][] generateAdjacencyMatrixDAG()
	{
		for (int i = 0; i < V-1 ; i++)
		{
			for (int j = 0; j < V ; j++)
			{
				if (j >= i )
				{
					adjacencyMatrizDAG[i][j]=0;
				}
				else
				{
					double temp = Math.random();
					if (temp<=0.5)
					{
						adjacencyMatrizDAG[i][j]= 0;
					}
					else{
						adjacencyMatrizDAG[i][j]= 1;
					}

				}
			}
		}
		return adjacencyMatrizDAG;
	}
	
	/**
	 * Generates a DAG with V vertices
	 * @return The DAG with V vertices 
	 */
	public Digraph generateDAG()
	{
		this.generateAdjacencyMatrixDAG();
		System.out.println("DAG sucessfully generated with adjacency matrix:");
		Digraph G = new Digraph(V);
		for (int i = 0 ; i < V-1 ; i++)
		{
			for (int j = 0 ; j < V-1 ; j++)
			{
				System.out.print(adjacencyMatrizDAG[i][j]);
				if (adjacencyMatrizDAG[i][j] == 1)
				{
					G.addEdge(i, j);
				}
			}
			System.out.println("");
		}
		return G;
	}
}
