package EstructurasDeDatos;

import java.util.Random;

import edu.princeton.cs.algs4.Digraph;

//Generates a random connected DAG that has the number of vertices given
public class DagGenerator {
	
	// Number of vertices of the DAG
	private int V; 
	
	// Probability of setting 1 in the adjacency matrix of the graph
	double prob;
	
	// Adjacency matrix of the DAG
	private int adjacencyMatrizDAG [][];
	
	
	/**
	 * Creates a DAG generator. It generates DAG with V vertices and with a given density
	 * @param pV The amount of vertices of the DAG's that the generator will produce
	 * @param pProb A number that ranges between 0 and 1. The closest to 1, the denser the graph will be.
	 */
	public DagGenerator (int pV, double pProb)
	{
		prob = pProb;
		V= pV;
		adjacencyMatrizDAG = new int [V][V];
	}
	
	/**
	 * Generates the adjacency matrix of a DAG with V vertices
	 * @return The adjacency matrix of the DAG
	 */
	private int[][] generateAdjacencyMatrixDAG()
	{
		// Fills out the entries below the diagonal with 0's and 1's. 
		for (int i = 0; i < V ; i++)
		{
			for (int j = 0; j < i ; j++)
			{
				adjacencyMatrizDAG[j][i]=0;

				double temp = Math.random();
				if (temp<=prob)
				{
					adjacencyMatrizDAG[i][j]= 1;
				}
				else{
					adjacencyMatrizDAG[i][j]= 0;
				}
			}
		}
		// If i!=0, the i-th row cannot be entirely of 0's. If j != V-1, the j-th column cannot be entirely of 0's.  
		
		for (int i = 0 ; i < V ; i++)
		{
			int sumRow = 0;
			int sumColumn = 0;
			for (int j = 0 ; j < V ; j++)
			{
				if (adjacencyMatrizDAG[i][j]==1)
				{
					sumRow += 1;
				}
				if (adjacencyMatrizDAG[j][i]==1)
				{
					sumColumn += 1;
				}
			}
			if (i != 0 && sumRow == 0)
			{
				Random rand = new Random();
				int columnWhereAdding1 = rand.nextInt(i);
				adjacencyMatrizDAG[i][columnWhereAdding1] = 1;
			}
			if (i != V-1 && sumColumn == 0)
			{
				Random rand = new Random();
				int rowWhereAdding1 = rand.nextInt(V-1-i)+(i+1);
				adjacencyMatrizDAG[rowWhereAdding1][i] = 1;
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
		for (int i = 0 ; i < V ; i++)
		{
			for (int j = 0 ; j < V ; j++)
			{
				System.out.print(adjacencyMatrizDAG[i][j]);
				if (adjacencyMatrizDAG[i][j] == 1)
				{
					G.addEdge(j, i);
				}
			}
			System.out.println("");
		}
		return G;
	}
}
