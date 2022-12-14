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

import com.example.demo.model.Lector;
import com.example.demo.service.ILectoresService;


@Controller
@RequestMapping("/lectores")
public class LectoresController {
	
	@Autowired
	ILectoresService lectoresService;
	
	@GetMapping("")
	public String crudLectores(Model model) {
		return findPaginated(1, "id", "asc", model);
	}
	
	@GetMapping("/add")
	public String showNewLectorForm(Model model) {
		Lector Lector = new Lector();
		model.addAttribute("lector", Lector);
		return "lector/create";
	}

	@PostMapping("/save")
	public String saveLector(@ModelAttribute("lector") Lector lector) {
		// save Course to database
		lectoresService.saveLector(lector);
		return "redirect:/lectores";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

		Lector lector = lectoresService.getLectorById(id);
		model.addAttribute("lector", lector);
		return "lector/update";
	}

	@GetMapping("/delete/{id}")
	public String deleteLector(@PathVariable (value = "id") long id) {

		this.lectoresService.deleteLectorById(id);
		return "redirect:/lectores";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 4;

		Page<Lector> page = lectoresService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Lector> listLector = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listLectores", listLector);
		return "lector/index";
	}
}
