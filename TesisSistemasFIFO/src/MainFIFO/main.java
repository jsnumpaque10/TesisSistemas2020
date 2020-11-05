package MainFIFO;

import EstructurasDeDatosFIFO.ControllerFIFO;
import EstructurasDeDatosFIFO.DagGeneratorFIFO;
import edu.princeton.cs.algs4.Digraph;

public class main {

	public static void main(String[] args) {
		// Creates the DAG generator 
		DagGeneratorFIFO dagGenerator = new DagGeneratorFIFO(200,0.2);
		Digraph H = dagGenerator.generateDAG();
		
		// Creates the controller of the execution
		ControllerFIFO controlllerOfComputation =  new ControllerFIFO(H,3);
		
		//Start execution 
		controlllerOfComputation.startExecution();
		
	}
	
}
