package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Copia;

public interface ICopiasService {

	List<Copia> getAllCopias();

	void saveCopia(Copia copia);

	Copia getCopiaById(long id);

	void deleteCopiaById(long id);

	Page<Copia> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);

}
