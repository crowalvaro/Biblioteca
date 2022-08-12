package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Prestamo;

public interface IPrestamosService {

	List<Prestamo> getAllPrestamos();

	void savePrestamo(Prestamo prestamo) throws Exception;

	Prestamo getPrestamoById(long id);

	void deletePrestamoById(long id);

	Page<Prestamo> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);


}
