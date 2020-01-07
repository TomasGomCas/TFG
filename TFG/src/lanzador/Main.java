package lanzador;

import java.io.File;
import java.util.LinkedList;

import modelo.Application;
import modelo.Data;
import modelo.generador.ReaderSpring;
import modelo.generador.WritterSpring;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WritterSpring a = new WritterSpring();	
		ReaderSpring reader = new ReaderSpring();
		
		reader.read();
		
		a.createControllerFile();
		a.writeFile(a.writeController());
		
	}

}
