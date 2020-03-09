package lanzador;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


	@RequestMapping("/ProductosgetAll")
	public String ProductosgetAll () {
		return null;
	}



	@RequestMapping("/ProductosPost")
	public String ProductosPost (@RequestBody ProductosBody body) {

		return null;

	}



	@RequestMapping("/VentasgetAll")
	public String VentasgetAll () {
		return null;
	}



	@RequestMapping("/VentasPost")
	public String VentasPost (@RequestBody VentasBody body) {

		return null;

	}


}