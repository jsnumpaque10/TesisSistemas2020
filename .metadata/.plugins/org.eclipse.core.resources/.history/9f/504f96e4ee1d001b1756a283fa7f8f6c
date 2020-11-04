package EstructurasDeDatos;

import java.util.ArrayList;

public class ControllerStealing extends Thread {
	
	// Array that stores the processors
	
	ArrayList<Procesador> processors;
	
	// Computation that is going to be processed 
	MultithreadedComputation computation;
	
	
	public ControllerStealing(MultithreadedComputation pComputacion)
	{
		processors = new ArrayList<Procesador>();
		computation = pComputacion;
	}
	
	/**
	 * Add a processor to the work stealing controller
	 * @param procesador The processor that wants to be added
	 */
	public void addProcessor(Procesador procesador)
	{
		processors.add(procesador);
	}
	
	public void run()
	{	
		while(computation.numberOfVisitedVertices()!= computation.getNumberVerticesG())
		{
			if(computation.getNumberOfProcessorsStealing() == processors.size())
			{
				computation.setVertexToSteal(-2);
			}
			else if (computation.getNumberOfProcessorsStealing() != 0)
			{
				for (int i=0 ; i< processors.size(); i++)
				{
					if (processors.get(i).getIsStealing() == false && processors.get(i).setVertexToSteal())
					{
						try {
							sleep(300);
						} catch (Exception e) {
							// TODO: handle exception
						}
						break;
					}
				}
			}
		}
	}
	
	// Get methods
	
	public ArrayList<Procesador> getProcessors()
	{
		return processors;
	}

}
