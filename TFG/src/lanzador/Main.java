package lanzador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import modelo.Application;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		// Generate Spring Program
		Application.getInstance().generateProgramSpring();

		// WRITE CVS
		 
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("target\\generatedCode\\gs-rest-service-complete\\target\\sample.csv"));
			
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
	                .withHeader("ID", "Name", "Designation", "Company"));
			csvPrinter.printRecord("5", "Sundar Pichai", "CEO", "Google");
			csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
			csvPrinter.flush();  

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// READ CVS
		try {
		    // create a reader
		    Reader reader = Files.newBufferedReader(Paths.get("target\\generatedCode\\gs-rest-service-complete\\target\\sample.csv"));

		    // read csv file
		    Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
		    for (CSVRecord record : records) {

		        System.out.println("Record #: " + record.getRecordNumber());
		        System.out.println("ID: " + record.get(0));
		        System.out.println("Name: " + record.get(1));
		        System.out.println("Email: " + record.get(2));
		        System.out.println("Country: " + record.get(3));
		        
		    }

		    // close the reader
		    reader.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	
		
		
	}

}
