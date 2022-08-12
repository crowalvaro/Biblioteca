package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Copia;
import com.example.demo.model.Lector;
import com.example.demo.model.Prestamo;
import com.example.demo.model.PrestamoAux;
import com.example.demo.service.ICopiasService;
import com.example.demo.service.ILectoresService;
import com.example.demo.service.IPrestamosService;

@Controller
@RequestMapping("/prestamos")
public class PrestamosController {
	@Autowired
	IPrestamosService prestamosService;
	@Autowired
	ILectoresService lectoresService;
	@Autowired
	ICopiasService copiasService;
	
	
	@GetMapping("")
	public String crudPrestamos(Model model) {
		return findPaginated(1, "id", "asc", model);
	}

	@GetMapping("/add")
	public String showNewPrestamoForm(Model model) {
		
		List<Copia> listaCopias = copiasService.getAllCopias();
		
		PrestamoAux prestamo = new PrestamoAux();
		model.addAttribute("prestamo", prestamo);
		model.addAttribute("listaLectores", lectoresService.getAllLectores() );
		model.addAttribute("listaCopias", listaCopias);
		return "prestamo/create";
	}

	@PostMapping("/save")
	public String savePrestamo(@ModelAttribute("prestamo") PrestamoAux prestamoAux) {
	
		
		Copia copia = copiasService.getCopiaById(prestamoAux.getIdCopia());
		Lector lector = lectoresService.getLectorById(prestamoAux.getIdLector());
		
		Prestamo prestamo = new Prestamo();
		
		prestamo.setCopia(copia);
		prestamo.setLector(lector);
		prestamo.setInicio(prestamoAux.getInicio());
		prestamo.setFin(prestamoAux.getFin());
	
		prestamosService.savePrestamo(prestamo);
		return "redirect:/prestamos";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

		Prestamo prestamo = prestamosService.getPrestamoById(id);
		model.addAttribute("prestamo", prestamo);
		return "prestamo/update";
	}

	@GetMapping("/delete/{id}")
	public String deletePrestamo(@PathVariable (value = "id") long id) {

		this.prestamosService.deletePrestamoById(id);
		return "redirect:/prestamos";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 4;

		Page<Prestamo> page = prestamosService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Prestamo> listPrestamos = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listPrestamos", listPrestamos);
		return "prestamo/index";
	}
}
