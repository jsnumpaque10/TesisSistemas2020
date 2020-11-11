package MainPruebas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		PrintWriter out = new PrintWriter(new FileWriter("/Users/juansebastiannumpaque/TesisSistemas2020/Pruebas.txt")); 
		out.print("Hello "); 
		out.println("world"); 
		out.close();
		
	}

}
