package lanzador;

import java.io.File;

import modelo.generador.WritterSpring;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WritterSpring a = new WritterSpring();
		a.Copiar(new File("target\\baseCode\\gs-rest-service-complete"), new File("target\\generatedCode"));
		
	}

}
