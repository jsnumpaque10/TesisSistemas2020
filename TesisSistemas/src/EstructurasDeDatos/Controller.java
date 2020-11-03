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
		multithreadedComputation = new MultithreadedComputation(pG,numberOfProcessors);
		workStealingController = new ControllerStealing(multithreadedComputation);

		//Create and store the processors
		for (int i = 0 ; i < numberOfProcessors ; i++)
		{
			workStealingController.addProcessor(new Procesador(multithreadedComputation,i));
			
			System.out.println("Processor with id " + i + " has been created.");
		}
		
		processors = workStealingController.getProcessors();
		
	}
	
	// Beginning of the execution
	
	public void startExecution()
	{	
		// Starts each one of the processors
		for ( int i = 0 ; i < processors.size(); i++)
		{
			if(i == 0)
			{
				//Notifies that each processor has started its execution
				System.out.println("Processor " + i + " has started the execution.");
				// Starts the work stealing controller
				System.out.println("Work stealing controller has started execution.");
				workStealingController.start();
				processors.get(i).start();
			}
			else
			{
				//Notifies that each processor has started its execution
				System.out.println("Processor " + i + " has started the execution.");
				processors.get(i).start();
			}

		}
		
	}
	

}
