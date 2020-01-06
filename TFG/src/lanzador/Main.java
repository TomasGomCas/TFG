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

		//WritterSpring a = new WritterSpring();
		//a.Copiar(new File("target\\baseCode\\gs-rest-service-complete"), new File("target\\generatedCode"));
		
		ReaderSpring reader = new ReaderSpring();
		reader.read();
		
		System.out.println("PROGRAM: " + Application.getInstance().getProgram().toString());
		
		
	}

}
