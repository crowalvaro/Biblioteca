package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Lector;

public interface ILectoresService {

	void saveLector(Lector lector);

	Lector getLectorById(long id);

	void deleteLectorById(long id);

	Page<Lector> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);

	List<Lector> getAllLectores();

}
