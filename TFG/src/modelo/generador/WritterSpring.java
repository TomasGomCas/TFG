package modelo.generador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

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
	public void write(String rutaSalida) {

		FD = new File(rutaSalida);
		fichero = new File(rutaSalida + "\\gs-rest-service-complete\\src\\main\\java\\lanzador\\Controller.java");
		aux = 0;
		copy(FO, FD);
		createControllerFile();
		writeFile(writeController());
		writeFileClass();
		writeFileDBClass();
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
				writer = new BufferedWriter(new FileWriter(FD.getAbsolutePath()
						+ "\\gs-rest-service-complete\\src\\main\\java\\lanzador\\" + service.getName() + "Body.java"));
				writer.write(writeBodyClass(service));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void writeFileDBClass() {

		for (Service service : Application.getInstance().getProgramRest().getServices()) {

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\" + service.getName() + "DB.csv"));

				ArrayList<String> ar = new ArrayList<String>();
				for (Data data : service.getData()) {
					ar.add(data.getName());
				}

				String[] HEADERS = ar.toArray(new String[0]);
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS));
				csvPrinter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private String writeController() {

		String string = "package lanzador;\r\n" + "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
				+ "import java.util.UUID;\nimport org.springframework.web.bind.annotation.RequestBody;\n"
				+ "import org.springframework.web.bind.annotation.RequestParam;\r\n import java.io.BufferedWriter;\n"
				+ "import java.io.FileWriter;\n" + "import org.springframework.web.bind.annotation.RestController;\r\n"
				+ "import java.io.IOException;\n" + "import java.io.Reader;\n" + "import java.nio.file.Files;\n"
				+ "import java.nio.file.Paths;\n" + "\n" + "import org.apache.commons.csv.CSVFormat;\n"
				+ "import org.apache.commons.csv.CSVRecord;\n"
				+ "import org.springframework.web.bind.annotation.RequestBody;\nimport org.apache.commons.csv.CSVPrinter;\n"
				+ "import org.springframework.web.bind.annotation.RequestMapping;\n"
				+ "import org.springframework.web.bind.annotation.RestController; \nimport java.util.LinkedList;\n"
				+ "\r\n" + "@RestController\r\n" + "public class Controller {\n\n";

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

		String str = "@RequestMapping(\"/" + service.getName() + "getAll\")\n\n" + "	public LinkedList<"
				+ service.getName() + "Body> " + service.getName() + "getAll () {\n\n" + "" + "\tLinkedList<"
				+ service.getName() + "Body> lista = new LinkedList<" + service.getName() + "Body>();\n" + "\n"
				+ "		try {\n" + "\n" + "			Reader reader = Files.newBufferedReader(Paths.get(\"target\\\\"
				+ service.getName() + "DB.csv\"));\n" + "\n"
				+ "			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);\n"
				+ "			int aux = 0;\n" + "			for (CSVRecord record : records) {\n" + "\n"
				+ "				if (aux != 0) {\n" + "					" + service.getName() + "Body valor = new "
				+ service.getName() + "Body(";

		int i = 0;
		for (Data data : service.getData()) {
			if (i < service.getData().size() - 1)
				str += "\"\",";
			else
				str += "\"\"";
			i++;
		}

		str += ");\n";

		int aux = 0;
		for (Data data : service.getData()) {
			str += "					valor.set" + data.getName() + "(record.get(" + aux + "));\n";
			aux++;
		}

		str += "					lista.add(valor);\n" + "				}\n" + "				aux++;\n"
				+ "			}\n" + "\n" + "			reader.close();\n" + "\n" + "		} catch (IOException ex) {\n"
				+ "			ex.printStackTrace();\n" + "		}\n" + "\n" + "		return lista;"

				+ "\n" + "	}\n\n";

		return str;
	}

	private String writePost(Service service) {

		String str = "@RequestMapping(\"/" + service.getName() + "Post\")\n" + "	public String " + service.getName()
				+ "Post (@RequestBody " + service.getName() + "Body body) {\n" + "\n"

				+ "		try {\n" + "			BufferedWriter writer = new BufferedWriter(new FileWriter(\"target\\\\"
				+ service.getName() + "DB.csv\", true));\n" + "\n"
				+ "			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);\n"
				+ "			csvPrinter.printRecord(";

		int i = 0;
		for (Data data : service.getData()) {
			if (i < service.getData().size() - 1)
				str += " body.get" + data.getName() + "(),";
			else
				str += " UUID.randomUUID().toString()";

			i++;
		}

		str += ");\n" + "\n" + "			csvPrinter.flush();\n" + "			return \"true\";\n"
				+ "		} catch (IOException e) {\n" + "			// TODO Auto-generated catch block\n"
				+ "			e.printStackTrace();\n" + "		}\n" + "\n" + "		return \"false\";"

				+ "}\n\n";

		return str;
	}

	private String writeBodyClass(Service service) {

		String retorno = "package lanzador;" + "\n\npublic class " + service.getName() + "Body {\n\n";

		// Se escriben los parametros
		for (Data data : service.getData()) {
			retorno += "\tprivate String " + data.getName() + ";\n";
		}

		// Se escribe el constructor
		retorno += "\n	public " + service.getName() + "Body (";
		int aux = 0;
		for (Data data : service.getData()) {
			if (aux < service.getData().size() - 1)
				retorno += "String " + data.getName() + ",";
			else
				retorno += "String " + data.getName() + "";
			aux++;
		}

		retorno += ") {";
		for (Data data : service.getData()) {
			retorno += "\n\tthis. " + data.getName() + " = " + data.getName() + ";";
		}
		retorno += "\n\t}\n\n";

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
