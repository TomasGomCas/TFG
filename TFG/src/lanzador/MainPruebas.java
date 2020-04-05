package lanzador;

import java.io.IOException;

public class MainPruebas {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		FileInputStream excelFile = new FileInputStream("C:\\Users\\TomasVM\\Desktop\\excel.xlsx");
//		Workbook workbook = new XSSFWorkbook(excelFile);
//		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//
//		Cell cell = workbook.getSheetAt(0).getRow(0).getCell(0);
//		cell.setCellFormula("SUM(Tabla1[Precio])");
//
//		CellValue c = evaluator.evaluate(cell);
//		System.out.println(c.getNumberValue());

		// -----------------------------------------

//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet(serviceName + "DB");
//		int rowCount = 0;
//
//		CSVParser parser = new CSVParser(
//				new FileReader(
//						"C:\\Users\\TomasVM\\Desktop\\gs-rest-service-complete\\target\\" + serviceName + "DB.csv"),
//				CSVFormat.DEFAULT);
//		List<CSVRecord> list = parser.getRecords();
//		for (CSVRecord record : list) {
//			Row row = sheet.createRow(rowCount);
//			rowCount++;
//
//			String[] arr = new String[record.size()];
//			int i = 0;
//			for (String str : record) {
//				Cell cell = row.createCell(i);
//				cell.setCellValue(str);
//				arr[i++] = str;
//			}
//		}
//
//		try (FileOutputStream outputStream = new FileOutputStream(
//				"C:\\Users\\TomasVM\\Desktop\\gs-rest-service-complete\\target\\" + serviceName + "DB.xlsx")) {
//			workbook.write(outputStream);
//		}
//		parser.close();
//
//	}
	}
}
