package EstructurasDeDatos;

import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;

public class MultithreadedComputation {
	
	// The digraph that models the multithreaded computations
	private Digraph G;

	// Number of tasks/vertices of the multithreaded computation
	private int numberVerticesG; 
	
	// Array that stores, for each vertex, the number of incident vertices that have been visited
	private ArrayList<Integer> incidentVertices;
	
	// Array that tells if a given vertex has been visited. The i-th entry equals 0 if the vertex i has
	// not been visited yet and 1 if the vertex has been visited
	private ArrayList<Integer> visitedVertices;
	
	// Array that tells if a given vertex has been enqueued. The i-th entry equals 0 if the vertex i has
	// not been enqueued yet and the id of the processor if the task was enqueued in the ready dequeue of the latter
	private ArrayList<Integer> enqueuedVertices;
	
	// Array that stores all the tasks that can be stolen
	private Integer vertexToSteal;
	
	// Id of the processor that wants to steal a task
	private Integer idProcessorWantsToSteal;
	
	// Flag variable that indicates if there is a vertex/task to be stolen
	private Boolean availableVertexToSteal;

	// Object that will allow us to calculate the priority for each vertex in the computation
	private LongestPathDAG longestPath;
	
	// Array that stores the priority in the computation of each vertex;
	private ArrayList<Integer> priorityVertices;

	
	
	public MultithreadedComputation(Digraph pG)
	{
		G = pG ;
		numberVerticesG = G.V();
		incidentVertices = new ArrayList<Integer>();
		visitedVertices = new ArrayList<Integer>();
		vertexToSteal = -1;
		idProcessorWantsToSteal = -1;
		availableVertexToSteal = false;
		longestPath = new LongestPathDAG(G);
		priorityVertices = new ArrayList<Integer>();
		
		//Fill the incidentVertices array with the number of incident vertices to each vertex
		for (int v = 0 ; v < numberVerticesG; v++)
		{
			incidentVertices.add(G.indegree(v));
		}
		
		//Fill each visitedVertices and enqueuedVertices array entry with 0 and -1 respectively since none of the vertices of G have been visited or enqueued
		for (int v = 0 ; v < numberVerticesG ; v++)
		{
			visitedVertices.add(0);
			enqueuedVertices.add(-1);
		}
		
		//Fill each priorityVertices array entry with its corresponding vertex priority
		for (int v = 0; v < numberVerticesG ; v++ )
		{
			priorityVertices.add(longestPath.calculateLongestPathLengthFromVertex(v));
		}	
		
	}
	
	/**
	 * Counts the number of vertices that have been visited
	 * @return The number of vertices/tasks that have been visited/completed
	 */
	public Integer numberOfVisitedVertices()
	{
		Integer numVisitedVertices = 0;
		for (int i = 0 ; i < visitedVertices.size() ; i++)
		{
			numVisitedVertices = numVisitedVertices + visitedVertices.get(i);
		}
		return numVisitedVertices;
	}
	
	/**
	 * Updates visitedVertices, incidentVertices and enqueuedVertices arrays when a processor completes/visits a task/vertex
	 * @param vertex The vertex that was visited
	 * @param processor The id of the processor that visited the vertex
	 */
	public synchronized void updateVisitedVertices(Integer vertex, int processor) {
		visitedVertices.set(vertex, 1);
		Bag<Integer> adjacentVertices = (Bag<Integer>) G.adj(vertex);
		Iterator<Integer> iteratorAdjacentVertices = adjacentVertices.iterator();
		while(iteratorAdjacentVertices.hasNext())
		{
			Integer adjacentVertex = iteratorAdjacentVertices.next();
			incidentVertices.set(adjacentVertex, incidentVertices.get(adjacentVertex)-1);
			enqueuedVertices.set(adjacentVertex, processor);			
		}
		
	}	
	
	/**
	 * Returns a vertex/task that can be stolen
	 * @return The vertex/task to be stolen
	 */
	public synchronized Integer stealVertex(Integer id)
	{	
		// Set the Id of the processor that wants to steal a task
		idProcessorWantsToSteal = id;
		
		// While there is not vertex/task to steal processor waits
		while (availableVertexToSteal == false)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// Prints the id and the task that had been stolen by the processor
		System.out.println("Processor " + idProcessorWantsToSteal + " steals task/vertex " + this.vertexToSteal);
		
		// When available the vertex/task is assigned to the processor
		idProcessorWantsToSteal = -1;
		availableVertexToSteal = false;
		return vertexToSteal;
		
	}
	
	/**
	 * Sets a vertex/task to be stolen
	 * @param vertex
	 */
	public synchronized void setVertexToSteal( Integer vertex )
	{
		vertexToSteal = vertex;
		availableVertexToSteal =  true;
	}
	

	// Get methods
	
	public Boolean getAvailableVertexToSteal() {
		return availableVertexToSteal;
	}
	
	public Integer getIdProcessorWantsToSteal() {
		return idProcessorWantsToSteal;
	}

	public ArrayList<Integer> getEnqueuedVertices() {
		return enqueuedVertices;
	}


	public ArrayList<Integer> getIncidentVertices() {
		return incidentVertices;
	}


	public ArrayList<Integer> getVisitedVertices() {
		return visitedVertices;
	}

	public ArrayList<Integer> getPriorityVertices() {
		return priorityVertices;
	}
	
	public int getNumberVerticesG() {
		return numberVerticesG;
	}
	
	public Digraph getG() {
		return G;
	}



}
