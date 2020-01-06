package modelo.generador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import interfaces.Reader;

public class ReaderSpring implements Reader{

	private File file = new File("TFG\\target\\excel");
	
	@Override
	public void read() {

        FileInputStream excelFile;
		try {
			excelFile = new FileInputStream(file);
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
