package EstructurasDeDatosFIFO;

import java.util.ArrayList;


public class ControllerStealingFIFO extends Thread {
	
	// Array that stores the processors
	
	ArrayList<ProcesadorFIFO> processors;
	
	// Computation that is going to be processed 
	MultithreadedComputationFIFO computation;
	
	
	public ControllerStealingFIFO(MultithreadedComputationFIFO pComputacion)
	{
		processors = new ArrayList<ProcesadorFIFO>();
		computation = pComputacion;
	}
	
	/**
	 * Add a processor to the work stealing controller
	 * @param procesador The processor that wants to be added
	 */
	public void addProcessor(ProcesadorFIFO procesador)
	{
		processors.add(procesador);
	}
	
	public void run()
	{	
		while(computation.numberOfVisitedVertices()!= computation.getNumberVerticesG())
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
	
	// Get methods
	
	public ArrayList<ProcesadorFIFO> getProcessors()
	{
		return processors;
	}

}
