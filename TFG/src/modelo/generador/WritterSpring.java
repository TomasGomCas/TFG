package modelo.generador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import modelo.Application;
import modelo.Data;
import modelo.ProgramRest;
import modelo.Service;

public class WritterSpring implements Writter {

	// ATRIBUTES
	private int aux = 0;
	private File FO = new File("target\\baseCode\\gs-rest-service-complete");
	private File FD = new File("target\\generatedCode");

	private File fichero = new File(
			"target\\generatedCode\\gs-rest-service-complete\\src\\main\\java\\lanzador\\Controller.java");

	// METHODS
	@Override
	public void write() {
		aux = 0;
		copy(FO, FD);
		createControllerFile();
		writeFile(writeController());
		writeFileClass();
	}

	private void copy(File FO, File FD) {
		// si el origen no es una carpeta
		if (!FO.isDirectory()) {
			// Llamo la funcion que lo copia
			copyFile(FO, FD);
		} else {
			// incremento el contador de entradas a esta funci�n
			aux++;
			// solo se entra ac� cuando se quiera copiar una carpeta y
			// sea la primera es decir la carpeta padre
			if (aux == 1) {
				// Cambio la ruta destino por el nombre que tenia mas el nombre de
				// la carpeta padre
				FD = new File(FD.getAbsolutePath() + "/" + FO.getName());
				// si la carpeta no existe la creo
				if (!FD.exists()) {
					FD.mkdir();
				}
			}
			// obtengo el nombre de todos los archivos y carpetas que
			// pertenecen a este fichero(FO)
			String[] Rutas = FO.list();
			// recorro uno a uno el contenido de la carpeta

			/*
			 * IMPORTANTE:EL HML SE DESCONFIGURA Y NO ME DEJA PORNE LA LINEA FO SIGUIENTE
			 * BIEN, TENGO PROBLEMA CON EL SIGNO MENOR.SI UD LE PASA LO MISMO DESCARGE EL
			 * PROGRAMA CON EL LINK DE ABAJO Y V�ALO DE FOMA SEGURA
			 */
			for (int i = 0; i < Rutas.length; i++) {
				// establezco el nombre del nuevo archivo origen
				File FnueOri = new File(FO.getAbsolutePath() + "/" + Rutas[i]);
				// establezco el nombre del nuevo archivo destino
				File FnueDest = new File(FD.getAbsolutePath() + "/" + Rutas[i]);
				// si no existe el archivo destino lo creo
				if (FnueOri.isDirectory() && !FnueDest.exists()) {
					FnueDest.mkdir();
				}
				// uso recursividad y llamo a esta misma funcion has llegar
				// al ultimo elemento
				copy(FnueOri, FnueDest);
			}
		}

	}

	private void copyFile(File FO, File FD) {
		try {
			if (FO.exists()) {
				String copiar = "S";
				if (FD.exists()) {
				}
				copiar = "S";
				if (copiar.toUpperCase().equals("S")) {
					FileInputStream LeeOrigen = new FileInputStream(FO);
					OutputStream Salida = new FileOutputStream(FD);
					byte[] buffer = new byte[1024];
					int tamano;
					while ((tamano = LeeOrigen.read(buffer)) > 0) {
						Salida.write(buffer, 0, tamano);
					}
					Salida.close();
					LeeOrigen.close();
				}

			} else {
				System.out.println("El fichero a copiar no existe..." + FO.getAbsolutePath());
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void createControllerFile() {

		try {
			fichero.createNewFile();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	private void writeFile(String string) {

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fichero));
			writer.write(string);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeFileClass() {

		for (Service service : Application.getInstance().getProgramRest().getServices()) {

			BufferedWriter writer;
			try {
				writer = new BufferedWriter(
						new FileWriter("target\\generatedCode\\gs-rest-service-complete\\src\\main\\java\\lanzador\\"
								+ service.getName() + "Body.java"));
				writer.write(writeBodyClass(service));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private String writeController() {

		String string = "package lanzador;\r\n" + "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
				+ "import org.springframework.web.bind.annotation.RequestBody;\n"
				+ "import org.springframework.web.bind.annotation.RequestParam;\r\n"
				+ "import org.springframework.web.bind.annotation.RestController;\r\n" + "\r\n" + "@RestController\r\n"
				+ "public class Controller {\n";

		ProgramRest rest = Application.getInstance().getProgramRest();

		for (Service service : rest.getServices()) {
			string = string + writeService(service);
		}

		string = string + "\n}";
		return string;
	}

	private String writeService(Service service) {

		String str = "";
		str += writeGetAll(service);
		str += writePost(service);

		return str;
	}

	private String writeGetAll(Service service) {

		String str = "\n\n	@RequestMapping(\"/" + service.getName() + "getAll\")\n" + "	public String "
				+ service.getName() + "getAll () {\n" + "		return null;\n" + "	}\n\n";

		return str;
	}

	private String writePost(Service service) {

		String str = "\n\n	@RequestMapping(\"/" + service.getName() + "Post\")\n" + "	public String "
				+ service.getName() + "Post (@RequestBody " + service.getName() + "Body body) {\n" + "\n"
				+ "		return null;\n\n" + "	}\n\n";

		return str;
	}

	private String writeBodyClass(Service service) {

		String retorno = "package lanzador;" + "\npublic class " + service.getName() + "Body {\n\n";

		// Se escriben los parametros
		for (Data data : service.getData()) {
			retorno += "private String " + data.getName() + ";\n\n";
		}

		// Se escriben los getters
		for (Data data : service.getData()) {
			retorno += "	public String get" + data.getName() + "() {\n" + "		return " + data.getName() + ";\n"
					+ "	}\n\n" + "";
		}

		// Se escriben los setters
		for (Data data : service.getData()) {
			retorno += "	public void set" + data.getName() + "(String " + data.getName() + ") {\n" + "		this."
					+ data.getName() + " = " + data.getName() + ";\n" + "	}\n\n ";
		}

		retorno += "\n}";

		return retorno;
	}
}
