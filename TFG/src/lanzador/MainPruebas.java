package lanzador;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MainPruebas {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream excelFile = new FileInputStream("C:\\Users\\TomasVM\\Desktop\\excel.xlsx");
		Workbook workbook = new XSSFWorkbook(excelFile);
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		Cell cell = workbook.getSheetAt(0).getRow(0).getCell(0);
		cell.setCellFormula("SUM(Ventas!B:B)");

		CellValue c = evaluator.evaluate(cell);
		System.out.println(c.getNumberValue());
		excelFile.close();
		workbook.close();

	}
}
