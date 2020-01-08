package lanzador;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/suma")
	public Integer suma(@RequestParam(value="sumando1") Integer sumando1,@RequestParam(value="sumando2") Integer sumando2)
	{
	return null;
	}

	@RequestMapping("/resta")
	public Integer resta(@RequestParam(value="restando1") Integer restando1,@RequestParam(value="restando2") Integer restando2)
	{
	return null;
	}

	@RequestMapping("/nombre")
	public String nombre(@RequestParam(value="cadena") String cadena,@RequestParam(value="cadena2") String cadena2)
	{
	return null;
	}

	@RequestMapping("/soloSalida")
	public String soloSalida()
	{
	return null;
	}

}