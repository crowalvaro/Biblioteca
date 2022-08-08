package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LibrosDao;
import com.example.demo.model.Libro;

@Service
public class LibrosSevice implements ILibrosService{
	@Autowired
	private LibrosDao librosDao;

	@Override
	public List<Libro> getAllLibros() {
		return librosDao.findAll();
	}

	@Override
	public void saveLibro(Libro libro) {
		this.librosDao.save(libro);
	}

	@Override
	public Libro getLibroById(long id) {
		Optional<Libro> optionalCourse = librosDao.findById(id);
		Libro libro = null;
		if (optionalCourse.isPresent()) {
			libro = optionalCourse.get();
		} else {
			throw new RuntimeException("Libro no encontrado por id : " + id);
		}
		return libro;
	}

	@Override
	public void deleteLibroById(long id) {
		this.librosDao.deleteById(id);
	}

	@Override
	public Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
		return this.librosDao.findAll(pageable);
	}
}
