package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CopiasDao;

import com.example.demo.model.Copia;

@Service
public class CopiasService implements ICopiasService{
	@Autowired
	private CopiasDao copiasDao;

	@Override
	public List<Copia> getAllCopias() {
		return copiasDao.findAll();
	}

	@Override
	public void saveCopia(Copia copia) {
		this.copiasDao.save(copia);
	}

	@Override
	public Copia getCopiaById(long id) {
		Optional<Copia> optionalCopia = copiasDao.findById(id);
		Copia copia = null;
		if (optionalCopia.isPresent()) {
			copia = optionalCopia.get();
		} else {
			throw new RuntimeException("Copia no encontrada por id : " + id);
		}
		return copia;
	}

	@Override
	public void deleteCopiaById(long id) {
		this.copiasDao.deleteById(id);
	}

	@Override
	public Page<Copia> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
		return this.copiasDao.findAll(pageable);
	}
}
