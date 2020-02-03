package hotel.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.model.Servicio;
import hotel.repository.IServicioRepository;

@Controller
public class ServicioController {

	@Autowired
	private IServicioRepository repoServicio;
	
	
	@GetMapping("/formAgregarServicio")
	public ModelAndView formAgregarServicio() {

		HashMap<String, Object> datos= new HashMap<String, Object>();
		
		Servicio servicio = new Servicio();
		
		datos.put("servicio", servicio);
		datos.put("lista", repoServicio.findAll());
		
		return new ModelAndView("formAgregarServicio").addAllObjects(datos);
	}
	
	@PostMapping("/agregarServicio")
	public ModelAndView agregarServicio(@ModelAttribute("servicio") Servicio servicio) {
		if(repoServicio.existsByNombre(servicio.getNombre())) {
			return new ModelAndView("confirmacion").addObject("mensaje", "El servicio ya estaba cargado.");
		}else
		{
			Servicio aux = repoServicio.save(servicio);
			if(aux.equals(servicio))
				return new ModelAndView("confirmacion").addObject("mensaje", "El servicio se agrego con exito.");
			else
				return new ModelAndView("confirmacion").addObject("mensaje", "Error al agregar el servicio.");
		}
	}
	
}
