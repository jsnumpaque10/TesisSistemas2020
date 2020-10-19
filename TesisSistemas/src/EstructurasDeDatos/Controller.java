package EstructurasDeDatos;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	
	// Beginning of the execution
	
	public void startExecution()
	{
		long startTime = System.nanoTime();
		
		// Starts each one of the processors
		for ( int i = 0 ; i < processors.size(); i++)
		{
			//Notifies that each processor has started its execution
			System.out.println("Processor " + i + " has started the execution.");
			processors.get(i).start();
		}
		
		// Starts the work stealing controller
		workStealingController.start();
		
		long stopTime = System.nanoTime();
		
		//Prints the total time of execution of the computation
		long totalExecutionTime = stopTime - startTime;
		System.out.println("The total execution time of the computation was: " + totalExecutionTime + " nanoseconds.");
	}

}
