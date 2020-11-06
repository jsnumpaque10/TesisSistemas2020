package EstructurasDeDatosFIFO;

import java.util.ArrayList;


import edu.princeton.cs.algs4.Digraph;

public class ControllerFIFO {
	
	// The multithreaded computation
	
	MultithreadedComputationFIFO multithreadedComputation;
	
	// Work stealing controller
	ControllerStealingFIFO workStealingController;
	
	// Arraylist of processors
	ArrayList<ProcesadorFIFO> processors;
	
	public ControllerFIFO (Digraph pG, int numberOfProcessors)
	{
		multithreadedComputation = new MultithreadedComputationFIFO(pG,numberOfProcessors);
		workStealingController = new ControllerStealingFIFO(multithreadedComputation);

		//Create and store the processors
		for (int i = 0 ; i < numberOfProcessors ; i++)
		{
			workStealingController.addProcessor(new ProcesadorFIFO(multithreadedComputation,i));
			
			System.out.println("FIFO processor with id " + i + " has been created.");
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
				System.out.println("FIFO processor " + i + " has started the execution.");
				// Starts the work stealing controller
				System.out.println("Work stealing FIFO controller has started execution. ");
				workStealingController.start();
				processors.get(i).start();
			}
			else
			{
				//Notifies that each processor has started its execution
				System.out.println("FIFO processor " + i + " has started the execution.");
				processors.get(i).start();
			}

		}
		
	}
	

}
