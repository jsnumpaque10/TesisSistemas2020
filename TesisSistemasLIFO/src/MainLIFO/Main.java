package MainLIFO;

import EstructurasDeDatosLIFO.ControllerLIFO;
import EstructurasDeDatosLIFO.DagGeneratorLIFO;
import edu.princeton.cs.algs4.Digraph;

public class Main {
	
	public static void main(String[] args) {
		// Creates the DAG generator 
		DagGeneratorLIFO dagGenerator = new DagGeneratorLIFO(40);
		Digraph H = dagGenerator.generateDAG();
		
		// Creates the controller of the execution
		ControllerLIFO controlllerOfComputation =  new ControllerLIFO(H,2);
		
		//Start execution 
		controlllerOfComputation.startExecution();
		
	}

}
