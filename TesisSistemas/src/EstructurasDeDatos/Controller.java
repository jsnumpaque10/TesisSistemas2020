package EstructurasDeDatos;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;

public class Controller {
	
	// Digraph that models the multithreaded computation
	Digraph G;
	
	// The multithreaded computation
	
	MultithreadedComputation multithreadedComputation;
	
	// Number of processors that will execute the computation
	int numberOfProcessors;
	
	// Work stealing controller
	ControllerStealing workStealingController;
	
	// Arraylist of processors
	ArrayList<Procesador> processors;
	
	public Controller (Digraph pG, int pNumberOfProcessors)
	{
		G = pG;
		numberOfProcessors = pNumberOfProcessors;
		multithreadedComputation = new MultithreadedComputation(G);
		workStealingController = new ControllerStealing(multithreadedComputation);

		
		//Create and store the processors
		for (int i = 0 ; i < numberOfProcessors ; i++)
		{
			workStealingController.addProcessor(new Procesador(multithreadedComputation,i+1));
		}
		
		processors = workStealingController.getProcessors();
		
	}
	
	public void startExecution()
	{
		for ( int i = 0 ; i < processors.size(); i++)
		{
			processors.get(i).start();
		}
	}

}
