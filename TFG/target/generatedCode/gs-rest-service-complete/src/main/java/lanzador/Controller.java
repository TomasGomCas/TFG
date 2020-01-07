

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
@RequestMapping("/suma")
public String suma(@RequestParam(value=" sumando1") dataInteger sumando1,@RequestParam(value=" sumando2") dataInteger sumando2)
{
return null}
@RequestMapping("/resta")
public String resta(@RequestParam(value=" restando1") dataInteger restando1,@RequestParam(value=" restando2") dataInteger restando2)
{
return null}}