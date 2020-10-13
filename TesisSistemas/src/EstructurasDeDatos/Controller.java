package EstructurasDeDatos;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;

public class Controller {
	
	// The multithreaded computation
	
	MultithreadedComputation multithreadedComputation;
	
	// Work stealing controller
	ControllerStealing workStealingController;
	
	// Arraylist of processors
	ArrayList<Procesador> processors;
	
	public Controller (Digraph pG, int numberOfProcessors)
	{
		multithreadedComputation = new MultithreadedComputation(pG);
		workStealingController = new ControllerStealing(multithreadedComputation);

		//Create and store the processors
		for (int i = 0 ; i < numberOfProcessors ; i++)
		{
			workStealingController.addProcessor(new Procesador(multithreadedComputation,i));
			
			System.out.println("Processor with id " + i + " has been created.");
		}
		
		processors = workStealingController.getProcessors();
		
	}
	
	public void startExecution()
	{
		// Starts the work stealing controller
		workStealingController.start();
		
		// Starts each one of the processors
		for ( int i = 0 ; i < processors.size(); i++)
		{
			processors.get(i).start();
		}
	}

}
