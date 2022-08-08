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

import com.example.demo.model.Libro;
import com.example.demo.service.ILibrosService;




@Controller
@RequestMapping("/libros")
public class LibrosController {
	
	@Autowired
	ILibrosService librosService;
	
	@GetMapping("")
	public String crudLibros(Model model) {
		return findPaginated(1, "titulo", "asc", model);
	}
	
	@GetMapping("/add")
	public String showNewLibroForm(Model model) {
		Libro Libro = new Libro();
		model.addAttribute("libro", Libro);
		return "libro/create";
	}

	@PostMapping("/save")
	public String saveLibro(@ModelAttribute("libro") Libro libro) {
		// save Course to database
		librosService.saveLibro(libro);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

		Libro libro = librosService.getLibroById(id);
		model.addAttribute("libro", libro);
		return "libro/update";
	}

	@GetMapping("/delete/{id}")
	public String deleteLibro(@PathVariable (value = "id") long id) {

		this.librosService.deleteLibroById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 4;

		Page<Libro> page = librosService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Libro> listLibros = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listLibros", listLibros);
		return "libro/index";
	}
}
