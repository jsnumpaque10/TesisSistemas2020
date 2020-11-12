package MainPruebas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import EstructurasDeDatos.Controller;
import EstructurasDeDatos.DagGenerator;
import EstructurasDeDatosFIFO.ControllerFIFO;
import EstructurasDeDatosLIFO.ControllerLIFO;
import edu.princeton.cs.algs4.Digraph;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		// Array that contains the amount of vertices of the graphs that are going to be tested
		int[] tamanos = new int[6];
		tamanos[0]=50;
		tamanos[1]=100;
		tamanos[2]=200;
		tamanos[3]=400;
		tamanos[4]=800;
		tamanos[5]=1600;
		
		// Array containing densities 
		double[] densidades = new double[3];
		densidades[0]= 0.2;
		densidades[1]= 0.5;
		densidades[2]= 0.8;
		
		
		// File where the results of the test are going to be printed
		PrintWriter out = new PrintWriter(new FileWriter("/Users/juansebastiannumpaque/TesisSistemas2020/Pruebas.txt")); 
		out.println("Tests Work Stealing Algorithms - Juan Sebastián Numpaque Roa"); 
		out.println("------------------------------------------------------------");
		
		// Tests executed
		for(int i= 0 ; i < tamanos.length; i++)
		{
			for(int j = 0 ; j < densidades.length ; j++)
			{
				out.println("Number of vertices of the graphs: "+ tamanos[i]);
				out.println("Density of the graphs: "+densidades[j]);
				out.println("------------------------------------------------------------");
				
				for(int k = 1 ; k <= 30 ; k++)
				{
					out.println("Iteration "+ k);
					out.println("------------------------------------------------------------");
					
					// Creates the DAG generator 
					DagGenerator dagGenerator = new DagGenerator(tamanos[i],densidades[j]);
					Digraph H = dagGenerator.generateDAG();
					
					for (int l = 0 ; l <= 4 ; l++)
					{
						// Creates the controller of the execution
						Controller controlllerComputation =  new Controller(H,l);
						controlllerComputation.startExecution();
						out.println("------------------------------------------------------------");
						ControllerFIFO controllerFIFOComputation  = new ControllerFIFO(H,l);
						controllerFIFOComputation.startExecution();
						out.println("------------------------------------------------------------");
						ControllerLIFO controllerLIFOComputation  = new ControllerLIFO(H,l);
						controllerLIFOComputation.startExecution();
						out.println("------------------------------------------------------------");
					}
					
				}
			}
		}
		
		out.close();
		
	}

}
