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
	
	// Array that stores the processors
	
	ArrayList<Procesador> processors;
	
	public Controller (Digraph pG, int pNumberOfProcessors)
	{
		G = pG;
		numberOfProcessors = pNumberOfProcessors;
		multithreadedComputation = new MultithreadedComputation(G);
		
		//Create and store the processors
		for (int i = 0 ; i < numberOfProcessors ; i++)
		{
			processors.add(new Procesador(multithreadedComputation,i+1));
		}
		
	}
	
	public void startExecution()
	{
		for ( int i = 0 ; i < processors.size(); i++)
		{
			processors.get(i).start();
		}
	}

}
