package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Libro;

public interface ILibrosService {


	Libro getLibroById(long id);

	void saveLibro(Libro libro);

	void deleteLibroById(long id);

	Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);

	List<Libro> getAllLibros();
	
}
