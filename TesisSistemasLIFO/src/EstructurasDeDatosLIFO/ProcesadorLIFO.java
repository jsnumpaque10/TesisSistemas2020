package EstructurasDeDatosLIFO;

import java.util.ArrayList;

public class ProcesadorLIFO extends Thread {
	
	// The multithreaded computation that the processor has to execute
	MultithreadedComputationLIFO computation;
	
	// Id of the processor
	Integer id;
	
	// Array that stores the spawned task/vertices that need to be enqueued
	ArrayList<Integer> verticesToEnqueue;
	
	// Ready dequeue of the processor which will be ordered by priority of the vertices/tasks
	ArrayList <Integer> readyDequeue;
	
	// Flag variable that indicates if a the processor is stealing
	private Boolean isStealing;
	
	// Amount of task executed by the processor
	int tasksExecuted;
	
	// Processor's execution time
	long executionTime;
	
	/**
	 * Constructor of the class
	 * @param pComputation The computation that the processor is going to execute
	 * @param pId The id of the processor
	 */
	public ProcesadorLIFO(MultithreadedComputationLIFO pComputation, int pId) {	
		
		computation =pComputation;
		id = pId;
		isStealing = false;
		verticesToEnqueue = new ArrayList<Integer>();
		readyDequeue = new ArrayList<Integer>();
		executionTime=0;
	}
	
	/**
	 * Visits a vertex in the ready dequeue of the processor. 
	 * If after visiting the vertex the ready dequeue of the processor is empty, the processor begins work stealing, else the processor visits the top priority vertex in the ready dequeue.
	 * If the incident vertices haven't been visited, the processor stalls.
	 * @param vertex The vertex to be visited
	 */
	public synchronized void visitVertex(int indexVertexToVisit)
	{		
		if (readyDequeue.isEmpty())
		{
			//Prints the state of the processor 
			System.out.println("LIFO processor "+ this.id + " ready dequeue is empty.");
			
			this.steal();
		}
		else
		{
			Integer vertex = readyDequeue.get(indexVertexToVisit);
			
			// Visits the vertex if all of its predecessors have been visited, else the processor stalls. 
			
			if (computation.getIncidentVertices().get(vertex) == 0)
			{
				
				// Remove vertex from the ready dequeue of the processor
				readyDequeue.remove(indexVertexToVisit);
				
				// Prints the id of the processor and the vertex/task that has visited/completed
				System.out.println("LIFO processor " + this.id + " has completed task " + vertex + ".");
				
				// Adds one to the count of the tasks executed by the processor.
				tasksExecuted ++;

				// Update visitedVertices, incidentVertices and enqueuedVertices arrays in computation so that the vertex/task appears as visited/completed
				// Enqueue spawned tasks in the ready dequeue of the processor if exist and haven't been enqueued in other processor's ready dequeue
				verticesToEnqueue = computation.updateVisitedVertices(vertex,this.id);
				
				// Adds all the spawned tasks at the head of the ready dequeue following FIFO
				readyDequeue.addAll(0,verticesToEnqueue);			
			}
			else
			{
				//Prints the state of the processor 
				System.out.println("LIFO processor " + this.id + " has stalled.");
				
				//Processor enters in stall
				this.stall();
			}	
		}
	}
	
	/**
	 * If the processor is stalled seeks in its ready dequeue for a vertex to be visited (task to be executed), else the processor begins work stealing.
	 */
	private void stall() 
	{
		// Processor checks if there is any task/vertex in its ready dequeue that can be visited. If not, begins work stealing
		for (int i = 0 ; i < readyDequeue.size() ; i++)
		{
			if (computation.getIncidentVertices().get(readyDequeue.get(i).intValue())==0)
			{
				this.visitVertex(i);
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
		// Prints the state of the processor 
		System.out.println("Processor " + this.id + " is work stealing.");
		this.isStealing = true;
		
		if (computation.numberOfVisitedVertices()!=computation.getNumberVerticesG())
		{
			Integer stolenVertex = computation.stealVertex(this.id);
			
			// Prints the id and the task that had been stolen by the processor
			System.out.println("LIFO processor " + this.id + " steals task " + stolenVertex + ".");
			
			if (stolenVertex.intValue() != -1 && stolenVertex.intValue() != -2)
			{
				System.out.println("Task " + stolenVertex + " added to LIFO processor " + this.id + " ready dequeue.");
				readyDequeue.add(stolenVertex);
				this.visitVertex(readyDequeue.size()-1);
			}
			else if (stolenVertex.intValue() == -2)
			{
				this.visitVertex(0);
			}
		}
		this.isStealing=false;
	}
	
	/**
	 * When possible, a vertex/task is given to another processor to be visited. 
	 */
	public synchronized boolean setVertexToSteal()
	{
		Boolean vertexSet = false;
		if (this.readyDequeue.size()>1)
		{
			// Prints the task/vertex that is being given by the processor
			System.out.println("LIFO processor " + this.id + " gives task " + readyDequeue.get(0) + " to steal.");
			
			// Gives to steal the task that is at the tail of the ready dequeue
			computation.setVertexToSteal(this.readyDequeue.get(readyDequeue.size()-1));
			readyDequeue.remove(readyDequeue.size()-1);
			
			vertexSet = true;
		}
		return vertexSet;
	}
	
	/**
	 * Start method for a processor
	 */
	public void run()
	{
		
		long startTime = System.nanoTime();
		if (this.id==0)
		{
			readyDequeue.add(0);
		}
		while(computation.numberOfVisitedVertices()!= computation.getNumberVerticesG())
		{
			this.visitVertex(0);
			System.out.println("Number of completed tasks is " + computation.numberOfVisitedVertices()+".");
			try {
				sleep(150);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("LIFO processor " + this.id + " executed " + tasksExecuted + " tasks.");
		long finishTime = System.nanoTime();
		executionTime = finishTime-startTime;
		System.out.println("LIFO processor " + this.id + " has finished the execution in " + this.executionTime + " nanoseconds.");
	}
	
	//Get Methods 
	
	public Boolean getIsStealing()
	{
		return this.isStealing;
	}
	
	public long getExecutionTime()
	{
		return executionTime;
	}

}
