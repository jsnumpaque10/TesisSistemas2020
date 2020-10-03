package EstructurasDeDatos;

import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;



public class Procesador extends Thread{
	
	// The multithreaded computation that the processor has to execute
	MultithreadedComputation computation;
	
	// Id of the processor
	Integer id;
	
	// Ready dequeue of the processor which will be ordered by priority of the vertices/tasks
	ArrayList <Integer> readyDequeue;
	
	/**
	 * Constructor of the class
	 * @param pComputation The computation that the processor is going to execute
	 * @param pId The id of the processor
	 */
	public Procesador(MultithreadedComputation pComputation, int pId) {	
		
		computation =pComputation;
		id = pId;
		readyDequeue = new ArrayList<Integer>();
	}
	
	/**
	 * Visits a vertex in the ready dequeue of the processor. 
	 * If after visiting the vertex the ready dequeue of the processor is empty, the processor begins work stealing, else the processor visits the top priority vertex in the ready dequeue.
	 * If the incident vertices haven't been visited, the processor stalls.
	 * @param vertex The vertex to be visited
	 */
	public void visitVertex(Integer indexVertexToVisit)
	{		
		Integer vertex = readyDequeue.get(indexVertexToVisit);
		// vertex = -1 iff the processor's ready dequeue is empty. In this case processor begins work stealing
		if (vertex == -1)
		{
			this.steal();
		}
		// Visits the vertex if all of its predecessors have been visited, else the processor stalls. 
		else if (computation.getIncidentVertices().get(vertex) == 0)
		{
			// Remove vertex from the ready dequeue of the processor
			readyDequeue.remove(indexVertexToVisit);
			
			// Update visitedVertices, incidentVertices and enqueuedVertices arrays in computation so that the vertex/task appears as visited/completed
			computation.updateVisitedVertices(vertex,this.id);
			
			// Enqueue spawned tasks in the ready dequeue of the processor if exist
			Bag<Integer> adjacentVertices = (Bag<Integer>) computation.getG().adj(vertex);
			Iterator<Integer> iteratorAdjacentVertices = adjacentVertices.iterator();
			
			while(iteratorAdjacentVertices.hasNext())
			{
				Integer adjacentVertex = iteratorAdjacentVertices.next();
					
				if (readyDequeue.isEmpty() || computation.getPriorityVertices().get(adjacentVertex) >= computation.getPriorityVertices().get(readyDequeue.get(0)))
				{
					readyDequeue.add(0, adjacentVertex);
				}
				else if (computation.getPriorityVertices().get(adjacentVertex) <= computation.getPriorityVertices().get(readyDequeue.size()-1))
				{
					readyDequeue.add(adjacentVertex);
				}
				else
				{
					for (int i = 0 ; i < readyDequeue.size()-1 ; i++)
					{
						if (computation.getPriorityVertices().get(adjacentVertex) <= computation.getPriorityVertices().get(readyDequeue.get(i)) && computation.getPriorityVertices().get(adjacentVertex) >= computation.getPriorityVertices().get(readyDequeue.get(i+1)))
						{
							readyDequeue.add(i+1, adjacentVertex);
							break;
						}
					}
				}			

			}
		}
		else
		{
			this.stall(indexVertexToVisit);
		}
	}
	
	/**
	 * If the processor is stalled seeks in its ready dequeue for a vertex to be visited (task to be executed), else the processor begins work stealing.
	 */
	private void stall(int indexVertexToVisit) 
	{
		// Processor checks if there is any task/vertex in its ready dequeue that can be visited. If not, begins work stealing
		for (int i = indexVertexToVisit+1 ; i < readyDequeue.size() ; i++)
		{
			if (computation.getIncidentVertices().get(readyDequeue.get(i))==0)
			{
				this.visitVertex(readyDequeue.get(i));
				break;
			}
			else if (i == readyDequeue.size()-1)
			{
				this.steal();
			}
		}

	}
	
	/**
	 * Steals a vertex/task that is enqueued in another processor ready dequeue. 
	 */
	private void steal() 
	{
		if (computation.numberOfVisitedVertices()!=computation.getNumberVerticesG())
		{
			readyDequeue.add(computation.stealVertex());
			this.visitVertex(readyDequeue.size()-1);
		}
	}
	
	/**
	 * When possible, a vertex/task is given to another processor to be visited. 
	 */
	private void setVertexToSteal()
	{
		if (this.readyDequeue.size()>1)
		{
			readyDequeue.remove(1);
			computation.setVertexToSteal(this.readyDequeue.get(1));
		}
	}
	
	public void start()
	{
		if (this.id==0)
		{
			readyDequeue.add(0);
		}
		while(computation.numberOfVisitedVertices()!= computation.getNumberVerticesG())
		{
			this.setVertexToSteal();
			this.visitVertex(0);
		}
	}

}

