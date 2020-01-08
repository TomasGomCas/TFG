package lanzador;

import modelo.generador.ReaderSpring;
import modelo.generador.WritterSpring;

public class Main {

	public static void main(String[] args) {

		// Read excel file
		ReaderSpring reader = new ReaderSpring();
		reader.read();
		
		// Write the code
		WritterSpring a = new WritterSpring();	
		a.write();
		//a.createControllerFile();
		//a.writeFile(a.writeController());
		
	}

}
