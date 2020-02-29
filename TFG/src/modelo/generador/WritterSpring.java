package modelo.generador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Application;
import modelo.Data;
import modelo.DataInput;
import modelo.DataOutput;
import modelo.ProgramRest;
import modelo.Service;
import modelo.ServiceType;

/**
 * The Class WritterSpring.
 */
public class WritterSpring implements Writter {

	// ATRIBUTES
	/** The aux. */
	private int aux = 0;

	/** The fo. (File Origin), represents the rute of the origin */
	private File FO = new File("target\\baseCode\\gs-rest-service-complete");

	/** The fd. (File Destin), represents the rute of the destiny */
	private File FD = new File("target\\generatedCode");

	/** The fichero. */
	private File fichero = new File(
			"target\\generatedCode\\gs-rest-service-complete\\src\\main\\java\\lanzador\\Controller.java");

	// METHODS
	/**
	 * Write.
	 */
	@Override
	public void write() {
		aux = 0;
		copy(FO, FD);
		createControllerFile();
		writeFile(writeController());
		writeFileClass();
	}

	/**
	 * Copy.
	 *
	 * @param FO the fo
	 * @param FD the fd
	 */
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

	/**
	 * Copy file.
	 *
	 * @param FO the fo
	 * @param FD the fd
	 */
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

	/**
	 * Creates the controller file.
	 */
	private void createControllerFile() {

		try {
			fichero.createNewFile();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * Write file.
	 *
	 * @param string the string
	 */
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
			if (service.getTipoServicio() == ServiceType.POST) {
				BufferedWriter writer;
				try {
					writer = new BufferedWriter(new FileWriter(
							"target\\generatedCode\\gs-rest-service-complete\\src\\main\\java\\lanzador\\"
									+ service.getName() + "Body.java"));
					writer.write(writeBodyClass(service));
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Write service.
	 *
	 * @param service the service
	 * @return the string
	 */
	private String writeService(Service service) {

		String str = "";
		str += writeServiceHeader(service.getName(), service.getData(), service);

		str += "\n\t{" + "\n\t";

		str += "return " + writeServiceBody(service) + ";";

		str += "\n\t}\n";

		return str;
	}

	/**
	 * Write service header.
	 *
	 * @param mapping the mapping
	 * @param data    the data
	 * @return the string
	 */
	private String writeServiceHeader(String mapping, LinkedList<Data> data, Service service) {

		// ESCRIBIMOS LA CABECERA
		String string = "\n\t@RequestMapping(\"/" + mapping + "\")";
		Data dataAux = null;
		for (Data i : data) {
			if (i instanceof DataOutput)
				dataAux = i;
		}
		string += "\n\tpublic " + "Integer" + " " + mapping + "(";

		// ESCRIBIMOS LOS PARAMETROS
		int j = 0;
		for (Data i : data) {
			if (i instanceof DataInput) {
				dataAux = i;
				string = string + "@RequestParam(value=\"" + dataAux.getName() + "\") "
						+ dataAux.getDataType().toString() + " " + dataAux.getName() + "";
			}

			if (j < data.size() - 2)
				string = string + ",";
			j++;
		}

		if (service.getTipoServicio() == ServiceType.POST) {
			string += ",@RequestBody " + service.getName() + "Body json";
//			PONER EN LA FUNCIONALIDAD LA TRANSFORMACION A JSONOBJECT
//			JsonReader jsonReader = Json.createReader(new StringReader(json));
//			JsonObject object = jsonReader.readObject();
//			jsonReader.close();

		}

		string += ")";
		return string;
	}

	/**
	 * Write controller.
	 *
	 * @return the string
	 */
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

	/**
	 * Write service body.
	 *
	 * @param service the service
	 * @return the string
	 */
	private String writeServiceBody(Service service) {

		String string = service.getDataOutput().getValue();

		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("[A-Z][0-9]*").matcher(service.getDataOutput().getValue());

		while (m.find()) {
			allMatches.add(m.group());
		}

		for (String st : allMatches) {
			string = string.replaceAll(st, service.getDataInputByAddress(st).getName());
		}

		return string;
	}

	private String writeBodyClass(Service service) {

		String retorno = "package lanzador; \n\n import java.util.LinkedList;" + "\npublic class " + service.getName() + "Body {\n\n";

		// Se escriben los parametros
		for (DataInput data : service.getBody().getDataInputs()) {
			if(data.isArray() == false) retorno += "private String " + data.getName() + ";\n\n";
			else retorno += "private LinkedList<String> " + data.getName() + ";\n\n";
		}

		// Se escriben los getters
		for (DataInput data : service.getBody().getDataInputs()) {
			if(data.isArray() == false) retorno += "	public String get" + data.getName() + "() {\n" + "		return " + data.getName() + ";\n"
					+ "	}\n\n" + "";
			else retorno += "	public LinkedList<String> get" + data.getName() + "() {\n" + "		return " + data.getName() + ";\n"
					+ "	}\n\n" + "";
		}

		// Se escriben los setters TODO
		for (DataInput data : service.getBody().getDataInputs()) {
			if(data.isArray() == false) retorno += "	public void set" + data.getName() + "(String " + data.getName() + ") {\n" + "		this."
					+ data.getName() + " = " + data.getName() + ";\n" + "	}\n\n ";
			else retorno += "	public void set" + data.getName() + "(LinkedList<String> " + data.getName() + ") {\n" + "		this."
					+ data.getName() + " = " + data.getName() + ";\n" + "	}\n\n ";
		}

		retorno += "\n}";

		return retorno;
	}
}
