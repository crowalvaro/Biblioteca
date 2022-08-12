package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LectoresDao;
import com.example.demo.model.Lector;

@Service
public class LectoresService implements ILectoresService{
	@Autowired
	private LectoresDao lectoresDao;

	@Override
	public List<Lector> getAllLectores() {
		return lectoresDao.findAll();
	}

	@Override
	public void saveLector(Lector lector) {
		this.lectoresDao.save(lector);
	}

	@Override
	public Lector getLectorById(long id) {
		Optional<Lector> optionalLector = lectoresDao.findById(id);
		Lector lector = null;
		if (optionalLector.isPresent()) {
			lector = optionalLector.get();
		} else {
			throw new RuntimeException("Lector no encontrado por id : " + id);
		}
		return lector;
	}

	@Override
	public void deleteLectorById(long id) {
		this.lectoresDao.deleteById(id);
	}

	@Override
	public Page<Lector> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
		return this.lectoresDao.findAll(pageable);
	}
}
