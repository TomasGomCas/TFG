package modelo.generador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.Application;
import modelo.Data;
import modelo.DataInput;
import modelo.DataOutput;
import modelo.DataType;
import modelo.ProgramRest;
import modelo.Service;
import modelo.ServiceType;

public class ReaderSpring implements Reader{

	private File file = new File("C:\\Users\\Tomas\\Desktop\\Repositorios\\Workspace Java\\TFG\\TFG\\target\\excel\\excel.xlsx");
	
	@Override
	public void read() {

		init();
		
        FileInputStream excelFile = null;
		try {
			excelFile = new FileInputStream(file);
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        
	        for (int i = 0; i < workbook.getNumberOfSheets() ; i++) {
	        	
		        Sheet datatypeSheet = workbook.getSheetAt(i);
				ProgramRest rest = Application.getInstance().getProgramRest();
				rest.getServices().add(readService(datatypeSheet));

			}
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void init() {
		Application.getInstance().setProgramRest(new ProgramRest());
	}
	
	private Service readService(Sheet sheet) {

		Service service = new Service();
		
		service.setTipoServicio(ServiceType.GET);
		service.setName(sheet.getSheetName());
		
		for (Data data : readInputData(sheet)) {
			service.getData().add(data);
		}

		for (Data data : readOutputData(sheet)) {
			service.getData().add(data);
		}
		
		return service;
	}
	
	private LinkedList<Data> readInputData(Sheet sheet) {
		
		LinkedList<Data> inputData = new LinkedList<Data>();
		Iterator<Row> iterator = sheet.iterator();
		
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            if(cellIterator.hasNext()) {
            	cellIterator.next();
            }

            // If data, enter
            if(cellIterator.hasNext() && currentRow.getCell(0).toString().equalsIgnoreCase("Entrada"))
            {
                // Create the data
                DataInput data = new DataInput();
            	// Read the name of the data
            	Cell currentCell = cellIterator.next();
            	data.setName(currentCell.getStringCellValue());
            	// Read the type of the data
                currentCell = cellIterator.next();
                if (currentCell.getCellTypeEnum() == CellType.STRING) {
            		data.setDataType(DataType.String);
            		data.setValue(currentCell.getStringCellValue());
            		data.setAddress(currentCell.getAddress().toString());
                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
            		data.setDataType(DataType.Integer);
            		int aux = (int) currentCell.getNumericCellValue();
            		data.setValue(""+aux);
            		data.setAddress(currentCell.getAddress().toString());
                }
                // Add the data into the data array
                inputData.add(data);
            }
        }
        return inputData;
	}
	
	private LinkedList<Data> readOutputData(Sheet sheet) {
		
		LinkedList<Data> inputData = new LinkedList<Data>();
		Iterator<Row> iterator = sheet.iterator();
		
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            if(cellIterator.hasNext()) {
            	cellIterator.next();
            }

            // If data, enter
            if(cellIterator.hasNext() && currentRow.getCell(0).toString().equalsIgnoreCase("salida"))
            {
                // Create the data
                DataOutput data = new DataOutput();
            	// Read the name of the data
            	Cell currentCell = cellIterator.next();
            	data.setName(currentCell.getStringCellValue());
            	// Read the type of the data
                currentCell = cellIterator.next();
                if (currentCell.getCellTypeEnum() == CellType.STRING) {
            		data.setDataType(DataType.String);
            		data.setValue(currentCell.getStringCellValue());
            		data.setAddress(currentCell.getAddress().toString());
                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
            		data.setDataType(DataType.Integer);
            		int aux = (int) currentCell.getNumericCellValue();
            		data.setValue(""+aux);
            		data.setAddress(currentCell.getAddress().toString());
                } else if (currentCell.getCellTypeEnum() == CellType.FORMULA) {
            		data.setDataType(DataType.dataFormula);
            		data.setValue(currentCell.getCellFormula());
            		data.setAddress(currentCell.getAddress().toString());
                }
                // Add the data into the data array
                inputData.add(data);
            }
        }
        return inputData;
	}
	
} // END OF CLASS
