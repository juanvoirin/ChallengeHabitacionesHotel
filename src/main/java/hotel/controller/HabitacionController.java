package hotel.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hotel.model.Cliente;
import hotel.model.Habitacion;
import hotel.repository.IHabitacionRepository;
import hotel.repository.IServicioRepository;

@Controller
public class HabitacionController {

	@Autowired
	private IHabitacionRepository repo;
	@Autowired
	private IServicioRepository repoServicio;

	@GetMapping("/")
	public ModelAndView listaHabitaciones() {

		return new ModelAndView("listaHabitaciones").addObject("habitaciones", repo.findAll());
	}

	@GetMapping("/formAgregarHabitacion")
	public ModelAndView formAltaHabitacion() {

		Habitacion habitacion = new Habitacion();

		habitacion.setLimpieza(false);
		habitacion.setMantenimiento(false);
		habitacion.setOcupada(false);
		habitacion.setOcupantes(0);
		habitacion.setDiasOcupada(0);
		habitacion.setResponsable(null);
		
		HashMap<String, Object> datos= new HashMap<String, Object>();
		
		datos.put("habitacion", habitacion);
		datos.put("listaServicios", repoServicio.findAll());

		return new ModelAndView("formAgregarHabitacion").addAllObjects(datos);
	}

	@PostMapping("/agregarHabitacion")
	public ModelAndView agregarHabitacion(@ModelAttribute("habitacion") Habitacion habitacion) {

		Habitacion aux = repo.save(habitacion);

		if (aux.equals(habitacion)) {
			return new ModelAndView("confirmacion").addObject("mensaje", "Alta realizada con exito.");
		} else {
			return new ModelAndView("confirmacion").addObject("mensaje", "Error en la carga de la habitacion.");
		}
	}

	@GetMapping("/bajaHabitacion")
	public ModelAndView bajaHabitacion(@RequestParam(name = "idHabitacion", required = true) int id) {

		Habitacion habitacion = repo.getOne(id);
		if (habitacion.darDeBaja()) {
			Habitacion aux = repo.save(habitacion);
			if (aux.equals(habitacion))
				return new ModelAndView("confirmacion").addObject("mensaje", "Baja realizada con exito.");
			else
				return new ModelAndView("confirmacion").addObject("mensaje", "No se pudo realizar la baja.");
		} else {
			return new ModelAndView("confirmacion").addObject("mensaje",
					"La habitacion que quiere dar de baja tiene ocupantes.");
		}
	}

	@GetMapping("/formAltaHabitacion")
	public ModelAndView formAgregarCliente(@RequestParam(name = "idHabitacion", required = true) int id) {

		Cliente cliente = new Cliente();
		HashMap<String, Object> datos= new HashMap<String, Object>();
		datos.put("idHabitacion", id);
		datos.put("cliente", cliente);
		datos.put("ocupantes", 0);
		datos.put("dias", 0);
		
		return new ModelAndView("formAltaHabitacion").addAllObjects(datos);
	}

	@PostMapping("/altaHabitacion")
	public ModelAndView altaHabitacion(@ModelAttribute("cliente") Cliente cliente,
			@RequestParam(name = "idHabitacion", required = true) int id,
			@RequestParam(name = "ocupantes", required = true) int ocupantes,
			@RequestParam(name = "dias", required = true) int dias) {

		Habitacion habitacion = repo.getOne(id);

		if (habitacion.altaHabitacion(ocupantes, cliente, dias)) {
			Habitacion aux = repo.save(habitacion);

			if (aux.getResponsable().equals(cliente)) {
				return new ModelAndView("confirmacion").addObject("mensaje", "Cliente cargado con exito.");
			} else {
				return new ModelAndView("confirmacion").addObject("mensaje", "Error en la carga del cliente.");
			}
		} else
			return new ModelAndView("confirmacion").addObject("mensaje", "Habitacion ocupada.");

	}

	@GetMapping("/formModificarHabitacion")
	public ModelAndView formModificarHabitacion(@RequestParam(name = "idHabitacion", required = true) int id) {

		Habitacion habitacion = repo.getOne(id);
		
		HashMap<String, Object> datos= new HashMap<String, Object>();
		
		datos.put("habitacion", habitacion);
		datos.put("listaServicios", repoServicio.findAll());
		
		return new ModelAndView("formModificarHabitacion").addAllObjects(datos);
	}

	@PostMapping("/modificarHabitacion")
	public ModelAndView modificarHabitacion(@ModelAttribute("habitacion") Habitacion habitacion) {

		Habitacion aux = repo.save(habitacion);

		if (aux.equals(habitacion)) {
			return new ModelAndView("confirmacion").addObject("mensaje", "Modificacion realizada con exito.");
		} else {
			return new ModelAndView("confirmacion").addObject("mensaje", "Error en la carga de la habitacion.");
		}
	}

	@GetMapping("/limpiarHabitacion")
	public ModelAndView limpiarHabitacion(@RequestParam(name = "idHabitacion", required = true) int id) {

		Habitacion habitacion = repo.getOne(id);
		if (habitacion.limpieza()) {
			repo.save(habitacion);
			return new ModelAndView("confirmacion").addObject("mensaje", "La habitacion se esta limpieando.");
		} else
			return new ModelAndView("confirmacion").addObject("mensaje", "Error al querer limpiar la habitacion.");
	}

	@GetMapping("/dejarLimpiarHabitacion")
	public ModelAndView dejarLimpiarHabitacion(@RequestParam(name = "idHabitacion", required = true) int id) {

		Habitacion habitacion = repo.getOne(id);
		if (habitacion.dejarLimpiar()) {
			repo.save(habitacion);
			return new ModelAndView("confirmacion").addObject("mensaje", "La habitacion se dejo de limpiar.");
		} else
			return new ModelAndView("confirmacion").addObject("mensaje",
					"Error al querer dejar de limpiar la habitacion.");
	}

	@GetMapping("/mantenerHabitacion")
	public ModelAndView mantenerHabitacion(@RequestParam(name = "idHabitacion", required = true) int id) {

		Habitacion habitacion = repo.getOne(id);
		if (habitacion.mantenimiento()) {
			repo.save(habitacion);
			return new ModelAndView("confirmacion").addObject("mensaje", "La habitacion esta en mantenimiento.");
		} else
			return new ModelAndView("confirmacion").addObject("mensaje",
					"No es posible darle mantenimiento a la habitacion.");
	}

	@GetMapping("/sacarMantenimientoHabitacion")
	public ModelAndView sacarMantenimientoHabitacion(@RequestParam(name = "idHabitacion", required = true) int id) {

		Habitacion habitacion = repo.getOne(id);
		if (habitacion.sacarMantenimiento()) {
			repo.save(habitacion);
			return new ModelAndView("confirmacion").addObject("mensaje", "La habitacion se dejo de mantener.");
		} else
			return new ModelAndView("confirmacion").addObject("mensaje",
					"No es posible dejar de mantener la habitacion.");
	}

}
