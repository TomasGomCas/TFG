package lanzador;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/suma")
	public Integer suma(@RequestParam(value="sumando1") Integer sumando1,@RequestParam(value="sumando2") Integer sumando2)
	{
	return sumando1+sumando2;
	}

	@RequestMapping("/resta")
	public Integer resta(@RequestParam(value="restando1") Integer restando1,@RequestParam(value="restando2") Integer restando2)
	{
	return restando1-restando2;
	}

	@RequestMapping("/compleja")
	public Integer compleja(@RequestParam(value="valor1") Integer valor1,@RequestParam(value="valor2") Integer valor2,@RequestParam(value="valor3") Integer valor3,@RequestParam(value="valor4") Integer valor4,@RequestBody complejaBody json)
	{
	return (valor1/4)*valor2+valor3*valor4;
	}

	@RequestMapping("/post")
	public Integer post(@RequestParam(value="valor1") Integer valor1,@RequestBody postBody json)
	{
	return 5;
	}

	@RequestMapping("/post2")
	public Integer post2(,@RequestBody post2Body json)
	{
	return 5;
	}

}