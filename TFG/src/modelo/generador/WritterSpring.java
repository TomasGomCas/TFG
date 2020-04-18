package modelo.generador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableStyleInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.Application;
import modelo.Data;
import modelo.ProgramRest;
import modelo.Service;

/**
 * The Class WritterSpring.
 */
public class WritterSpring implements Writter {

	/** The aux. */
	// ATRIBUTES
	private int aux = 0;

	/** The fo. */
	private File FO = new File("target\\baseCode\\gs-rest-service-complete");

	/** The fd. */
	private File FD = new File("target\\generatedCode");

	/** The fichero. */
	private File fichero = new File(
			"target\\generatedCode\\gs-rest-service-complete\\src\\main\\java\\lanzador\\Controller.java");

	/**
	 * Write.
	 *
	 * @param rutaSalida the ruta salida
	 */
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
		try {
			createExcelDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Copy.
	 *
	 * @param FO the fo
	 * @param FD the fd
	 */
	private void copy(File FO, File FD) {

		if (!FO.isDirectory()) {
			copyFile(FO, FD);
		} else {
			aux++;
			if (aux == 1) {
				FD = new File(FD.getAbsolutePath() + "/" + FO.getName());
				if (!FD.exists()) {
					FD.mkdir();
				}
			}

			String[] Rutas = FO.list();
			for (int i = 0; i < Rutas.length; i++) {

				File FnueOri = new File(FO.getAbsolutePath() + "/" + Rutas[i]);

				File FnueDest = new File(FD.getAbsolutePath() + "/" + Rutas[i]);

				if (FnueOri.isDirectory() && !FnueDest.exists()) {
					FnueDest.mkdir();
				}
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

	/**
	 * Write file class.
	 */
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

	/**
	 * Write file DB class.
	 */
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

	/**
	 * Write controller.
	 *
	 * @return the string
	 */
	private String writeController() {

		String string = "package lanzador;\n" + "\n" + "import java.io.BufferedWriter;\n" + "import java.io.File;\n"
				+ "import java.io.FileInputStream;\n" + "import java.io.FileOutputStream;\n"
				+ "import java.io.FileReader;\n" + "import java.io.FileWriter;\n" + "import java.io.IOException;\n"
				+ "import java.io.Reader;\n" + "import java.nio.file.Files;\n" + "import java.nio.file.Paths;\n"
				+ "import java.util.LinkedList;\n" + "import java.util.List;\n" + "import java.util.UUID;\n" + "\n"
				+ "import org.apache.commons.csv.CSVFormat;\n import java.io.FileOutputStream;\n"
				+ "import org.apache.commons.csv.CSVParser;\n" + "import org.apache.commons.csv.CSVPrinter;\n"
				+ "import org.apache.commons.csv.CSVRecord;\n" + "import org.apache.poi.ss.usermodel.Cell;\n"
				+ "import org.apache.poi.ss.usermodel.CellValue;\n"
				+ "import org.apache.poi.ss.usermodel.FormulaEvaluator;\n import org.apache.poi.ss.usermodel.CellType;\n"
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

	/**
	 * Write service.
	 *
	 * @param service the service
	 * @return the string
	 */
	private String writeService(Service service) {

		String str = "";
		str += writeGetAll(service);
		str += writeGetById(service);
		str += writePost(service);
		str += writeDelete(service);
		str += writePut(service);

		return str;
	}

	/**
	 * Write get all.
	 *
	 * @param service the service
	 * @return the string
	 */
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
						+ "		Cell cell" + aux + " = workbook" + aux + ".getSheet(\"" + service.getName()
						+ "\").getRow(aux).getCell(" + aux + ");\n" + "		cell" + aux + ".setCellFormula(\""
						+ data.getFormulaValue() + "\");\n" + "\n" + "		CellValue c" + aux + " = evaluator" + aux
						+ ".evaluate(cell" + aux + ");\n" + "		\n" + "" + "					workbook" + aux
						+ ".getSheet(\"" + service.getName() + "\").getRow(aux).getCell(" + aux + ").setCellValue(c"
						+ aux + ".getNumberValue());\n" + "					workbook" + aux + ".getSheet(\""
						+ service.getName() + "\").getRow(aux).getCell(" + aux + ").setCellType(CellType.NUMERIC);\n"
						+ "					FileOutputStream fileOut" + aux
						+ " = new FileOutputStream(\"target\\\\ExcelDB.xlsx\");\n" + "					workbook" + aux
						+ ".write(fileOut" + aux + ");		excelFile" + aux + ".close();\n" + "		workbook" + aux
						+ ".close();";

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

	/**
	 * Write get by id.
	 *
	 * @param service the service
	 * @return the string
	 */
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
						+ "		Cell cell" + aux + " = workbook" + aux + ".getSheet(\"" + service.getName()
						+ "\").getRow(aux).getCell(" + aux + ");\n" + "		cell" + aux + ".setCellFormula(\""
						+ data.getFormulaValue() + "\");\n" + "\n" + "		CellValue c" + aux + " = evaluator" + aux
						+ ".evaluate(cell" + aux + ");\n" + "					FileOutputStream fileOut" + aux
						+ " = new FileOutputStream(\"target\\\\ExcelDB.xlsx\");\n" + "					workbook" + aux
						+ ".write(fileOut" + aux + ");		excelFile" + aux + ".close();\n"
						+ "		workbook0.close();		\n" + "		excelFile" + aux + ".close();\n"
						+ "		workbook" + aux + ".close();";

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

	/**
	 * Write delete.
	 *
	 * @param service the service
	 * @return the string
	 */
	private String writeDelete(Service service) {

		String str = "";

		str += "	@RequestMapping(value = \"/" + service.getName() + "\", method = RequestMethod.DELETE)\n"
				+ "	public boolean " + service.getName() + "Delete(@RequestParam(value = \"id\") String id) {\n" + "\n"
				+ "boolean retorno = false;\n		// leer records\n" + "		LinkedList<" + service.getName()
				+ "Body> lista = " + service.getName() + "getAll();\n" + "		LinkedList<" + service.getName()
				+ "Body> listaNueva = new LinkedList<" + service.getName() + "Body>();\n" + "		for ("
				+ service.getName() + "Body row : lista) {\n" + "			if (!row.getid().equals(id))\n"
				+ "				listaNueva.add(row);\n			else {\n" + "				retorno = true;\n"
				+ "			}" + "		}\n" + "\n" + "		// Escribir records\n" + "		try {\n"
				+ "			BufferedWriter writer;\n"
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
				+ "return retorno;\n" + "	}\n";

		return str;
	}

	/**
	 * Write post.
	 *
	 * @param service the service
	 * @return the string
	 */
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
			if (i < service.getData().size() - 1) {
				if (!data.isFormula()) {
					str += " body.get" + data.getName() + "(),";
				} else {
					str += "\"=" + data.getFormulaValue() + "\",";
				}
			} else {
				str += " UUID.randomUUID().toString()";
			}

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

	/**
	 * Write put.
	 *
	 * @param service the service
	 * @return the string
	 */
	private String writePut(Service service) {

		String str = "";

		str += "	\n@RequestMapping(value = \"/" + service.getName() + "\", method = RequestMethod.PUT)\n"
				+ "	public boolean " + service.getName() + "PUT(@RequestParam(value = \"id\") String id,@RequestBody "
				+ service.getName() + "Body body) {\n" + "\n" + "boolean retorno = false;\n		// leer records\n"
				+ "		LinkedList<" + service.getName() + "Body> lista = " + service.getName() + "getAll();\n"
				+ "		LinkedList<" + service.getName() + "Body> listaNueva = new LinkedList<" + service.getName()
				+ "Body>();\n" + "		for (" + service.getName() + "Body row : lista) {\n"
				+ "			if (row.getid().equals(id)){\n"
				+ "row = body;\nrow.setid(id);\n\tretorno = true;				}listaNueva.add(row);\n" + "		}\n"
				+ "\n" + "		// Escribir records\n" + "		try {\n" + "			BufferedWriter writer;\n"
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
				+ "return retorno;\n" + "	}\n\n";

		return str;
	}

	/**
	 * Write body class.
	 *
	 * @param service the service
	 * @return the string
	 */
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

	/**
	 * Write import CVS.
	 *
	 * @return the string
	 */
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
				+ "			AreaReference reference = wb.getCreationHelper().createAreaReference(new CellReference(0, 0),\n"
				+ "					new CellReference(list.size(), numHeaders - 1));\n" + "\n"
				+ "			XSSFTable table = sheet.createTable(reference); // creates a table having 3 columns as of area reference\n"
				+ "\n" + "			table.setName(serviceName);\n" + "			table.setDisplayName(serviceName);\n"
				+ "\n" + "			table.getCTTable().addNewTableStyleInfo();\n"
				+ "			table.getCTTable().getTableStyleInfo().setName(serviceName);\n" + "\n"
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
				+ "						cell.setCellValue(record.get(j).toString());\n"
				+ "						FileOutputStream fileOut = new FileOutputStream(\"target\\\\ExcelDB.xlsx\");\n"
				+ "						wb.write(fileOut);\n" + "					} else {\n"
				+ "						if (record.get(j).toString().matches(\"[0-9]*\")) {\n"
				+ "							if (!record.get(j).toString().equals(\"\")) {\n"
				+ "								cell.setCellValue(new Integer(record.get(j).toString()));\n"
				+ "							} else {\n" + "								cell.setCellValue(\"\");\n"
				+ "							}\n" + "						} else {\n"
				+ "							cell.setCellValue(record.get(j).toString());\n"
				+ "							if (record.get(j).toString().contains(\"=\")) {\n"
				+ "								cell.setCellFormula(record.get(j).toString().replace(\"=\", \"\"));\n"
				+ "							}\n" + "\n" + "						}\n" + "					}\n"
				+ "				}\n" + "				i++;\n" + "			}\n" + "			parser.close();\n"
				+ "\n" + "			FileOutputStream fileOut = new FileOutputStream(\"target\\\\ExcelDB.xlsx\");\n"
				+ "			wb.write(fileOut);\n" + "\n" + "		} catch (Exception e) {\n"
				+ "			e.printStackTrace();\n" + "		}\n" + "	}\n";

		return retorno;
	}

	/**
	 * Creates the excel DB.
	 *
	 * @throws Exception the exception
	 */
	private void createExcelDB() throws Exception {

		Workbook workbook = new XSSFWorkbook();

		FileOutputStream fileOut2 = new FileOutputStream(
				FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\ExcelDB.xlsx");
		workbook.write(fileOut2);
		fileOut2.close();

		for (Service service : Application.getInstance().getProgramRest().getServices()) {

			try {

				CSVParser parser = new CSVParser(new FileReader(
						FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\" + service.getName() + "DB.csv"),
						CSVFormat.DEFAULT);
				List<CSVRecord> list = parser.getRecords();
				int numHeaders = list.get(0).size();

				FileInputStream inputStream = new FileInputStream(
						new File(FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\ExcelDB.xlsx"));
				Workbook wb = WorkbookFactory.create(inputStream);

				int auz = wb.getSheetIndex(service.getName());
				wb.createSheet(service.getName());
				XSSFSheet sheet = (XSSFSheet) wb.getSheet(service.getName());

				AreaReference reference = wb.getCreationHelper().createAreaReference(new CellReference(0, 0),
						new CellReference(list.size(), numHeaders - 1));

				XSSFTable table = sheet.createTable(reference); // creates a table having 3 columns as of area reference

				table.setName(service.getName());
				table.setDisplayName(service.getName());

				table.getCTTable().addNewTableStyleInfo();
				table.getCTTable().getTableStyleInfo().setName(service.getName());

				XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();
				style.setName(service.getName());
				style.setShowColumnStripes(true);
				style.setShowRowStripes(true);
				style.setFirstColumn(true);
				style.setLastColumn(true);
				style.setShowRowStripes(true);
				style.setShowColumnStripes(true);

				XSSFRow row;
				XSSFCell cell;
				int rowCount = 0;
				int i = 0;
				for (CSVRecord record : list) {
					// Create row
					row = sheet.createRow(i);
					for (int j = 0; j < numHeaders; j++) {
						// Create cell
						cell = row.createCell(j);
						if (i == 0) {
							cell.setCellValue(record.get(j).toString());
							FileOutputStream fileOut = new FileOutputStream(
									FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\ExcelDB.xlsx");
							wb.write(fileOut);
						} else {
							if (record.get(j).toString().matches("[0-9]*")) {
								if (!record.get(j).toString().equals("")) {
									cell.setCellValue(new Integer(record.get(j).toString()));
								} else {
									cell.setCellValue("");
								}
							} else {
								cell.setCellValue(record.get(j).toString());
								if (record.get(j).toString().contains("=")) {
									cell.setCellFormula(record.get(j).toString().replace("=", ""));
								}

							}
						}
					}
					i++;
				}
				parser.close();

				FileOutputStream fileOut = new FileOutputStream(
						FD.getAbsolutePath() + "\\gs-rest-service-complete\\target\\ExcelDB.xlsx");
				wb.write(fileOut);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
