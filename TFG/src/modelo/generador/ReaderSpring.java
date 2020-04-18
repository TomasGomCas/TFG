package modelo.generador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.Application;
import modelo.Data;
import modelo.ProgramRest;
import modelo.Service;

/**
 * The Class ReaderSpring.
 */
public class ReaderSpring implements Reader {

	/** The file. */
	// ATRIBUTES
	private File file = new File("target\\excel\\excel.xlsx");

	// CONSTRUCTORS

	/**
	 * Inits the reader.
	 */
	// METHODS
	private void init() {
		Application.getInstance().setProgramRest(new ProgramRest());
	}

	/**
	 * Read. Encargado de leer
	 * 
	 * @param rutaEntrada the ruta entrada
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void read(String rutaEntrada) throws IOException {

		file = new File(rutaEntrada);
		init();
		readSheet();

	}

	/**
	 * Read sheet.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readSheet() throws IOException {

		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			Service service = new Service();
			service.setName(sheet.getSheetName());

			readTableHeaders(service, sheet);
			readBodySheet(service, sheet);

			Application.getInstance().getProgramRest().getServices().add(service);
		}

	}

	/**
	 * Read table headers.
	 *
	 * @param service the service
	 * @param sheet   the sheet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readTableHeaders(Service service, Sheet sheet) throws IOException {

		for (Cell mycell : sheet.getRow(0)) {
			Data dataInput = new Data();
			dataInput.setName(mycell.toString());
			dataInput.setAddress(mycell.getAddress().toString());
			service.getData().add(dataInput);
		}
		Data dataInput = new Data();
		dataInput.setName("id");
		dataInput.setAddress("0");
		service.getData().add(dataInput);
	}

	/**
	 * Read body sheet.
	 *
	 * @param service the service
	 * @param sheet   the sheet
	 */
	private void readBodySheet(Service service, Sheet sheet) {

		Iterator<Row> iterator = sheet.iterator();
		iterator.next();

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				if (currentCell.getCellType() == CellType.FORMULA) {
					service.getData().get(currentCell.getColumnIndex()).setFormula(true);
					service.getData().get(currentCell.getColumnIndex()).setFormulaValue(currentCell.getCellFormula());
				}

			}
		}

	}

	// DELEGATED METHODS

	// GETTERS AND SETTERS

}
