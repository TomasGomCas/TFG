package modelo.generador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
		createExcelDB();
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

		String string = "package lanzador;\n" + "\n" + "import java.io.BufferedWriter;\n" + "import java.io.File;\n"
				+ "import java.io.FileInputStream;\n" + "import java.io.FileOutputStream;\n"
				+ "import java.io.FileReader;\n" + "import java.io.FileWriter;\n" + "import java.io.IOException;\n"
				+ "import java.io.Reader;\n" + "import java.nio.file.Files;\n" + "import java.nio.file.Paths;\n"
				+ "import java.util.LinkedList;\n" + "import java.util.List;\n" + "import java.util.UUID;\n" + "\n"
				+ "import org.apache.commons.csv.CSVFormat;\n" + "import org.apache.commons.csv.CSVParser;\n"
				+ "import org.apache.commons.csv.CSVPrinter;\n" + "import org.apache.commons.csv.CSVRecord;\n"
				+ "import org.apache.poi.ss.usermodel.Cell;\n" + "import org.apache.poi.ss.usermodel.CellValue;\n"
				+ "import org.apache.poi.ss.usermodel.FormulaEvaluator;\n"
				+ "import org.apache.poi.ss.usermodel.Workbook;\n"
				+ "import org.apache.poi.ss.usermodel.WorkbookFactory;\n"
				+ "import org.apache.poi.ss.util.AreaReference;\n" + "import org.apache.poi.ss.util.CellReference;\n"
				+ "import org.apache.poi.xssf.usermodel.XSSFCell;\n" + "import org.apache.poi.xssf.usermodel.XSSFRow;\n"
				+ "import org.apache.poi.xssf.usermodel.XSSFSheet;\n"
				+ "import org.apache.poi.xssf.usermodel.XSSFTable;\n"
				+ "import org.apache.poi.xssf.usermodel.XSSFTableStyleInfo;\n"
				+ "import org.apache.poi.xssf.usermodel.XSSFWorkbook;\n"
				+ "import org.springframework.web.bind.annotation.RequestBody;\n"
				+ "import org.springframework.web.bind.annotation.RequestMapping;\n"
				+ "import org.springframework.web.bind.annotation.RequestMethod;\n"
				+ "import org.springframework.web.bind.annotation.RequestParam;\n"
				+ "import org.springframework.web.bind.annotation.RestController;" + "\r\n" + "@RestController\r\n"
				+ "public class Controller {\n\n";

		ProgramRest rest = Application.getInstance().getProgramRest();

		for (Service service : rest.getServices()) {
			string = string + writeService(service);
		}

		string += writeImportCVS();
		string = string + "\n}";
		return string;
	}

	private String writeService(Service service) {

		String str = "";
		str += writeGetAll(service);
		str += writeGetById(service);
		str += writePost(service);
		str += writeDelete(service);
		str += writePut(service);

		return str;
	}

	private String writeGetAll(Service service) {

		String str = "@RequestMapping(value = \"/" + service.getName() + "\", method = RequestMethod.GET)\n\n"
				+ "	public LinkedList<" + service.getName() + "Body> " + service.getName() + "getAll () {\n\n" + ""
				+ "\tLinkedList<" + service.getName() + "Body> lista = new LinkedList<" + service.getName()
				+ "Body>();\n" + "\n" + "		try {\n" + "\n"
				+ "			Reader reader = Files.newBufferedReader(Paths.get(\"target\\\\" + service.getName()
				+ "DB.csv\"));\n" + "\n"
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
			if (!data.isFormula()) {
				str += "					valor.set" + data.getName() + "(record.get(" + aux + "));\n";
				aux++;
			} else {

				str += "		FileInputStream excelFile" + aux
						+ " = new FileInputStream(\"target\\\\ExcelDB.xlsx\");\n" + "		Workbook workbook" + aux
						+ " = new XSSFWorkbook(excelFile" + aux + ");\n" + "		FormulaEvaluator evaluator" + aux
						+ " = workbook" + aux + ".getCreationHelper().createFormulaEvaluator();\n" + "\n"
						+ "		Cell cell" + aux + " = workbook" + aux + ".getSheetAt(0).getRow(0).getCell(0);\n"
						+ "		cell" + aux + ".setCellFormula(\"" + data.getFormulaValue() + "\");\n" + "\n"
						+ "		CellValue c" + aux + " = evaluator" + aux + ".evaluate(cell" + aux + ");\n" + "		\n"
						+ "		excelFile" + aux + ".close();\n" + "		workbook" + aux + ".close();";

				str += "					valor.set" + data.getName() + "(\"\" + c" + aux + ".getNumberValue());\n";
				aux++;
			}
		}

		str += "					lista.add(valor);\n" + "				}\n" + "				aux++;\n"
				+ "			}\n" + "\n" + "			reader.close();\n" + "\n" + "		} catch (IOException ex) {\n"
				+ "			ex.printStackTrace();\n" + "		}\n" + "\n" + ""
				+ "	\n							exportCVSintoEXCEL(\"" + service.getName() + "\");" + "\nreturn lista;"

				+ "\n" + "	}\n\n";

		return str;
	}

	private String writeGetById(Service service) {

		String str = "@RequestMapping(value = \"/" + service.getName() + "ID\", method = RequestMethod.GET)\n\n"
				+ "	public " + service.getName() + "Body " + service.getName()
				+ "getById (@RequestParam(value = \"id\") String id) {\n\n" + "" + "\tLinkedList<" + service.getName()
				+ "Body> lista = new LinkedList<" + service.getName() + "Body>();\n" + "\n" + "		try {\n" + "\n"
				+ "			Reader reader = Files.newBufferedReader(Paths.get(\"target\\\\" + service.getName()
				+ "DB.csv\"));\n" + "\n"
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
			if (!data.isFormula()) {
				str += "					valor.set" + data.getName() + "(record.get(" + aux + "));\n";
				aux++;
			} else {

				str += "		FileInputStream excelFile" + aux
						+ " = new FileInputStream(\"target\\\\ExcelDB.xlsx\");\n" + "		Workbook workbook" + aux
						+ " = new XSSFWorkbook(excelFile" + aux + ");\n" + "		FormulaEvaluator evaluator" + aux
						+ " = workbook" + aux + ".getCreationHelper().createFormulaEvaluator();\n" + "\n"
						+ "		Cell cell" + aux + " = workbook" + aux + ".getSheetAt(0).getRow(0).getCell(0);\n"
						+ "		cell" + aux + ".setCellFormula(\"" + data.getFormulaValue() + "\");\n" + "\n"
						+ "		CellValue c" + aux + " = evaluator" + aux + ".evaluate(cell" + aux + ");\n" + "		\n"
						+ "		excelFile" + aux + ".close();\n" + "		workbook" + aux + ".close();";

				str += "					valor.set" + data.getName() + "(\"\" + c" + aux + ".getNumberValue());\n";
				aux++;
			}
		}

		str += "					lista.add(valor);\n" + "				if (valor.getid().equals(id)) {"
				+ "\n						exportCVSintoEXCEL(\"" + service.getName() + "\");\n" + "return valor;"
				+ "}}\n" + "				aux++;\n" + "			}\n" + "\n" + "			reader.close();\n" + "\n"
				+ "		} catch (IOException ex) {\n" + "			ex.printStackTrace();\n" + "		}\n" + "\n"
				+ "		return  null;"

				+ "\n" + "	}\n\n";

		return str;
	}

	private String writeDelete(Service service) {

		String str = "";

		str += "	@RequestMapping(value = \"/" + service.getName() + "\", method = RequestMethod.DELETE)\n"
				+ "	public boolean " + service.getName() + "Delete(@RequestParam(value = \"id\") String id) {\n" + "\n"
				+ "		// leer records\n" + "		LinkedList<" + service.getName() + "Body> lista = "
				+ service.getName() + "getAll();\n" + "		LinkedList<" + service.getName()
				+ "Body> listaNueva = new LinkedList<" + service.getName() + "Body>();\n" + "		for ("
				+ service.getName() + "Body row : lista) {\n" + "			if (!row.getid().equals(id))\n"
				+ "				listaNueva.add(row);\n" + "		}\n" + "\n" + "		// Escribir records\n"
				+ "		try {\n" + "			BufferedWriter writer;\n"
				+ "			writer = new BufferedWriter(new FileWriter(\"target\\\\" + service.getName()
				+ "DB.csv\"));\n" + "\n" + "			CSVPrinter csvPrinter = new CSVPrinter(writer,\n"
				+ "					";

		// Escribimos cabeceras
		str += "CSVFormat.DEFAULT.withHeader(";
		int aux = 0;
		for (Data data : service.getData()) {
			if (aux < service.getData().size() - 1)
				str += "\"" + data.getName() + "\",";
			else
				str += "\"" + data.getName() + "\"));";
			aux++;
		}

		// Escribimos Items
		aux = 0;
		str += "			for (" + service.getName()
				+ "Body item : listaNueva) {\n			csvPrinter.printRecord(";
		for (Data data : service.getData()) {
			if (aux < service.getData().size() - 1)
				str += "item.get" + data.getName() + "(),";
			else
				str += "item.get" + data.getName() + "());}\n";
			aux++;
		}

		str += "			csvPrinter.flush();\n" + "\n" + "		} catch (IOException e) {\n"
				+ "			// TODO Auto-generated catch block\n" + "			e.printStackTrace();\n" + "		}\n"
				+ "\n" + "" + "\n						exportCVSintoEXCEL(\"" + service.getName() + "\");\n		"
				+ "return true;\n" + "	}\n";

		return str;
	}

	private String writePost(Service service) {

		String str = "@RequestMapping(value = \"/" + service.getName() + "\", method = RequestMethod.POST)\n"
				+ "	public String " + service.getName() + "Post (@RequestBody " + service.getName() + "Body body) {\n"
				+ "\n"

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

		str += ");\n" + "\n" + "			csvPrinter.flush();" + "\n"
				+ "	 						exportCVSintoEXCEL(\"" + service.getName()
				+ "\");				return \"true\";\n" + "		} catch (IOException e) {\n"
				+ "			// TODO Auto-generated catch block\n" + "			e.printStackTrace();\n" + "		}\n"
				+ "\n" + "" + "\n 						exportCVSintoEXCEL(\"" + service.getName() + "\");		"
				+ "\nreturn \"false\";"

				+ "}\n\n";

		return str;
	}

	private String writePut(Service service) {

		String str = "";

		str += "	\n@RequestMapping(value = \"/" + service.getName() + "\", method = RequestMethod.PUT)\n"
				+ "	public boolean " + service.getName() + "PUT(@RequestParam(value = \"id\") String id,@RequestBody "
				+ service.getName() + "Body body) {\n" + "\n" + "		// leer records\n" + "		LinkedList<"
				+ service.getName() + "Body> lista = " + service.getName() + "getAll();\n" + "		LinkedList<"
				+ service.getName() + "Body> listaNueva = new LinkedList<" + service.getName() + "Body>();\n"
				+ "		for (" + service.getName() + "Body row : lista) {\n"
				+ "			if (row.getid().equals(id)){\n"
				+ "row = body;\nrow.setid(id);				}listaNueva.add(row);\n" + "		}\n" + "\n"
				+ "		// Escribir records\n" + "		try {\n" + "			BufferedWriter writer;\n"
				+ "			writer = new BufferedWriter(new FileWriter(\"target\\\\" + service.getName()
				+ "DB.csv\"));\n" + "\n" + "			CSVPrinter csvPrinter = new CSVPrinter(writer,\n"
				+ "					";

		// Escribimos cabeceras
		str += "CSVFormat.DEFAULT.withHeader(";
		int aux = 0;
		for (Data data : service.getData()) {
			if (aux < service.getData().size() - 1)
				str += "\"" + data.getName() + "\",";
			else
				str += "\"" + data.getName() + "\"));";
			aux++;
		}

		// Escribimos Items
		aux = 0;
		str += "			for (" + service.getName()
				+ "Body item : listaNueva) {\n			csvPrinter.printRecord(";
		for (Data data : service.getData()) {
			if (aux < service.getData().size() - 1)
				str += "item.get" + data.getName() + "(),";
			else
				str += "item.get" + data.getName() + "());}\n";
			aux++;
		}

		str += "			csvPrinter.flush();\n" + "\n" + "		} catch (IOException e) {\n"
				+ "			// TODO Auto-generated catch block\n" + "			e.printStackTrace();\n" + "		}\n"
				+ "\n" + "" + "\n						exportCVSintoEXCEL(\"" + service.getName() + "\");		\n"
				+ "return true;\n" + "	}\n\n";

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

	private String writeImportCVS() {

		String retorno = "";

		retorno += "	private void exportCVSintoEXCEL(String serviceName) {\n" + "\n" + "		try {\n" + "\n"
				+ "			CSVParser parser = new CSVParser(new FileReader(\"target\\\\\" + serviceName + \"DB.csv\"), CSVFormat.DEFAULT);\n"
				+ "			List<CSVRecord> list = parser.getRecords();\n"
				+ "			int numHeaders = list.get(0).size();\n" + "\n"
				+ "			FileInputStream inputStream = new FileInputStream(new File(\"target\\\\ExcelDB.xlsx\"));\n"
				+ "			Workbook wb = WorkbookFactory.create(inputStream);\n" + "\n"
				+ "			int auz = wb.getSheetIndex(serviceName);\n" + "			wb.removeSheetAt(auz);\n"
				+ "			wb.createSheet(serviceName);\n"
				+ "			XSSFSheet sheet = (XSSFSheet) wb.getSheet(serviceName);\n" + "\n"
				+ "			// Set which area the table should be placed in\n"
				+ "			AreaReference reference = wb.getCreationHelper().createAreaReference(new CellReference(0, 0),\n"
				+ "					new CellReference(list.size(), numHeaders - 1));\n" + "\n" + "			// Create\n"
				+ "			XSSFTable table = sheet.createTable(reference); // creates a table having 3 columns as of area reference\n"
				+ "			// sheet.h\n" + "			// but all of those have id 1, so we need repairing\n"
				+ "//			table.getCTTable().getTableColumns().getTableColumnArray(1).setId(2);\n"
				+ "//			table.getCTTable().getTableColumns().getTableColumnArray(2).setId(3);\n" + "\n"
				+ "			table.setName(serviceName);\n" + "			table.setDisplayName(serviceName);\n" + "\n"
				+ "			// For now, create the initial style in a low-level way\n"
				+ "			table.getCTTable().addNewTableStyleInfo();\n"
				+ "			table.getCTTable().getTableStyleInfo().setName(serviceName);\n" + "\n"
				+ "			// Style the table\n"
				+ "			XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();\n"
				+ "			style.setName(serviceName);\n" + "			style.setShowColumnStripes(true);\n"
				+ "			style.setShowRowStripes(true);\n" + "			style.setFirstColumn(true);\n"
				+ "			style.setLastColumn(true);\n" + "			style.setShowRowStripes(true);\n"
				+ "			style.setShowColumnStripes(true);\n" + "\n" + "			XSSFRow row;\n"
				+ "			XSSFCell cell;\n" + "			int rowCount = 0;\n" + "			int i = 0;\n"
				+ "			for (CSVRecord record : list) {\n" + "				// Create row\n"
				+ "				row = sheet.createRow(i);\n"
				+ "				for (int j = 0; j < numHeaders; j++) {\n" + "					// Create cell\n"
				+ "					cell = row.createCell(j);\n" + "					if (i == 0) {\n"
				+ "						cell.setCellValue(record.get(j).toString());\n" + "					} else {\n"
				+ "						if (record.get(j).toString().matches(\"[0-9]*\")) {\n"
				+ "							if (!record.get(j).toString().equals(\"\")) {\n"
				+ "								cell.setCellValue(new Integer(record.get(j).toString()));\n"
				+ "							} else {\n" + "								cell.setCellValue(\"\");\n"
				+ "							}\n" + "						} else {\n"
				+ "							cell.setCellValue(record.get(j).toString());\n" + "						}\n"
				+ "					}\n" + "				}\n" + "				i++;\n" + "			}\n"
				+ "			parser.close();\n" + "\n"
				+ "			FileOutputStream fileOut = new FileOutputStream(\"target\\\\ExcelDB.xlsx\");\n"
				+ "			wb.write(fileOut);\n" + "\n" + "		} catch (Exception e) {\n"
				+ "			// TODO: handle exception\n" + "		}\n" + "	}";

		return retorno;
	}

	private void createExcelDB() {

		Workbook workbook = new XSSFWorkbook();

		for (Service service : Application.getInstance().getProgramRest().getServices()) {
			workbook.createSheet(service.getName());
		}

		try (FileOutputStream outputStream = new FileOutputStream(
				FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\" + "Excel" + "DB.xlsx")) {
			try {
				workbook.write(outputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
