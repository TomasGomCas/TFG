package lanzador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/ProductosgetAll")
	public LinkedList<ProductosBody> ProductosgetAll() {
		LinkedList<ProductosBody> lista = new LinkedList<ProductosBody>();

		try {

			Reader reader = Files.newBufferedReader(Paths.get("target\\ProductosDB.csv"));

			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
			int aux = 0;
			for (CSVRecord record : records) {

				if (aux != 0) {
					ProductosBody valor = new ProductosBody("", "");
					valor.setNombre(record.get(0));
					valor.setPrecio(record.get(1));
					lista.add(valor);
				}
				aux++;
			}

			reader.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return lista;
	}

	@RequestMapping("/ProductosPost")
	public String ProductosPost(@RequestBody ProductosBody body) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("target\\ProductosDB.csv", true));

			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
			csvPrinter.printRecord(body.getNombre(), body.getPrecio());

			csvPrinter.flush();
			return "true";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "false";
	}

	@RequestMapping("/VentasgetAll")
	public LinkedList<VentasBody> VentasgetAll() {
		LinkedList<VentasBody> lista = new LinkedList<VentasBody>();

		try {

			Reader reader = Files.newBufferedReader(Paths.get("target\\VentasDB.csv"));

			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
			int aux = 0;
			for (CSVRecord record : records) {

				if (aux != 0) {
					VentasBody valor = new VentasBody("", "");
					valor.setNombre(record.get(0));
					valor.setFecha(record.get(1));
					lista.add(valor);
				}
				aux++;
			}

			reader.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return lista;
	}

	@RequestMapping("/VentasPost")
	public String VentasPost(@RequestBody VentasBody body) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("target\\VentasDB.csv", true));

			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
			csvPrinter.printRecord(body.getNombre(), body.getFecha());

			csvPrinter.flush();
			return "true";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "false";
	}

}