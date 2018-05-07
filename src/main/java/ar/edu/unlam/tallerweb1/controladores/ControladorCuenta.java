package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCuenta {
	@RequestMapping (path="/{operacion}/{numero1}/{numero2}")
	public ModelAndView cuentaPathVariable(@PathVariable String operacion, @PathVariable Integer numero1,@PathVariable Integer numero2) {
		ModelMap model = new ModelMap();
		float resultado;

		switch(operacion) {

		case "suma":
			resultado= numero1+numero2;
			break;
		case "resta":
			resultado=numero1-numero2;
			break;
		case "multiplicacion":
			resultado=numero1*numero2;
			break;
		case "division":
			if(numero2==0) {
				return new ModelAndView ("redirect:/errorOperacion");
			}
			else {
				float num1=numero1;
				float num2=numero2;
				resultado=num1/num2;
			}
			break;
		default:
			return new ModelAndView ("redirect:/errorOperacion");
		}

		model.put("operacion", operacion);
		model.put("numero1", numero1);
		model.put("numero2", numero2);
		model.put("resultado", resultado);

		return new ModelAndView("cuenta",model);
	}

	@RequestMapping(path="/errorOperacion")
	public ModelAndView errorOperacion() {
		ModelMap model = new ModelMap();
		model.put("mensaje", "operacion incorrecta");
		return new ModelAndView ("errorOperacion", model);
	}

}


