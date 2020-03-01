package lanzador;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.FileWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/Post")
	public Integer Post(@RequestBody PostBody json) throws IOException
	{
		FileWriter fw = new FileWriter("target\\sample.csv", true);	
		CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT);
		
		csvPrinter.printRecord(json.getnombre(),json.getdinero());
		
		fw.close();	

		return null;
	}
	
	@RequestMapping("/recuperar")
	public LinkedList<PostBody> get(@RequestBody PostBody json) throws IOException
	{
	    Reader reader = Files.newBufferedReader(Paths.get("target\\sample.csv"));
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
	    
	    LinkedList<PostBody> lista = new LinkedList<PostBody>();
	    for (CSVRecord record : records) {
	    	PostBody a = new PostBody();
	    	a.setdinero(record.get(0));
	    	a.setnombre(record.get(1));
	    	lista.add(a);
	    }
	    reader.close();

		return lista;
	}

}