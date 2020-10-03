package EstructurasDeDatos;

import java.util.ArrayList;

public class ControllerStealing extends Thread {
	
	// Array that stores the processors
	
	ArrayList<Procesador> processors;
	
	
	public ControllerStealing()
	{
		processors = new ArrayList<Procesador>();
	}
	
	/**
	 * Add a processor to the work stealing controller
	 * @param procesador The processor that wants to be added
	 */
	public void addProcessor(Procesador procesador)
	{
		processors.add(procesador);
	}

}
