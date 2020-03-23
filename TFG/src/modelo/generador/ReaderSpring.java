package modelo.generador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.Application;
import modelo.Data;
import modelo.ProgramRest;
import modelo.Service;

public class ReaderSpring implements Reader {

	// ATRIBUTES
	private File file = new File("target\\excel\\excel.xlsx");

	// CONSTRUCTORS

	// METHODS
	private void init() {
		Application.getInstance().setProgramRest(new ProgramRest());
	}

	@Override
	public void read(String rutaEntrada) throws IOException {

		file = new File(rutaEntrada);
		init();
		readSheet();

	}

	private void readSheet() throws IOException {

		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			Service service = new Service();
			service.setName(sheet.getSheetName());

			readTableHeaders(service, sheet);

			Application.getInstance().getProgramRest().getServices().add(service);
		}

	}

	private void readTableHeaders(Service service, Sheet sheet) throws IOException {

		for (Cell mycell : sheet.getRow(0)) {
			Data dataInput = new Data();
			dataInput.setName(mycell.toString());
			service.getData().add(dataInput);
		}
		Data dataInput = new Data();
		dataInput.setName("id");
		service.getData().add(dataInput);

	}

	// DELEGATED METHODS

	// GETTERS AND SETTERS

}
