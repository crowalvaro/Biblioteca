package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PrestamosDao;
import com.example.demo.model.Prestamo;

@Service
public class PrestamosService implements IPrestamosService{
	@Autowired
	private PrestamosDao prestamosDao;

	@Override
	public List<Prestamo> getAllPrestamos() {
		return prestamosDao.findAll();
	}
	

	
	@Override
	public void savePrestamo(Prestamo prestamo) throws Exception {
		
		if(this.prestamosDao.numeroPrestamosLector(prestamo.getLector()).isPresent()) {
			Integer numeroPrestamos = this.prestamosDao.numeroPrestamosLector(prestamo.getLector()).get();
			
			if(numeroPrestamos<3) {
				this.prestamosDao.save(prestamo);
			}else {
				//Aqui deveria devolver error o no hacer nada ya que es cuando sea mayor que 3
				throw new Exception("El lector ya tiene 3 prestamos");
			}
			
		}else {
			this.prestamosDao.save(prestamo);
		}
		
		
	}

	@Override
	public Prestamo getPrestamoById(long id) {
		Optional<Prestamo> optionalPrestamo = prestamosDao.findById(id);
		Prestamo prestamo = null;
		if (optionalPrestamo.isPresent()) {
			prestamo = optionalPrestamo.get();
		} else {
			throw new RuntimeException("Prestamo no encontrado por id : " + id);
		}
		return prestamo;
	}

	@Override
	public void deletePrestamoById(long id) {
		this.prestamosDao.deleteById(id);
	}

	@Override
	public Page<Prestamo> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
		return this.prestamosDao.findAll(pageable);
	}
}
