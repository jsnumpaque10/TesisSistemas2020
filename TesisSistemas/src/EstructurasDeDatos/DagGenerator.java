package EstructurasDeDatos;

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
	
	public int[][] generateAdjacencyMatrixDAG()
	{
		
		return adjacencyMatrizDAG;
	}
}
