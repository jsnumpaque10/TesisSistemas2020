package Main;

import EstructurasDeDatos.Controller;
import EstructurasDeDatos.DagGenerator;
import edu.princeton.cs.algs4.Digraph;

public class main {

	public static void main(String[] args) {
		// Creates the DAG generator 
		DagGenerator dagGenerator = new DagGenerator(50);
		Digraph H = dagGenerator.generateDAG();
		
		// Creates the controller of the execution
		Controller controlllerOfComputation =  new Controller(H,2);
		
		//Start execution 
		controlllerOfComputation.startExecution();
		
	}

}
