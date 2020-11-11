package MainPruebas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		// Array que contiene la cantidad de vértices sobre los que se harán las pruebas
		int[] tamanos = new int[5];
		tamanos[0]=50;
		tamanos[1]=100;
		tamanos[2]=200;
		tamanos[3]=400;
		tamanos[4]=800;
		tamanos[5]=1600;
		
		// Array que contiene las densidades de loa grafos sobre las que se harán pruebas
		long[] densidades = new long[3];
		densidades[0]= (long) 0.2;
		densidades[1]= (long) 0.5;
		densidades[2]= (long) 0.9;
		
		
		
		PrintWriter out = new PrintWriter(new FileWriter("/Users/juansebastiannumpaque/TesisSistemas2020/Pruebas.txt")); 
		out.print("Hello "); 
		out.println("world");
		out.print("Me caga esta mierda");
		out.close();
		
	}

}
