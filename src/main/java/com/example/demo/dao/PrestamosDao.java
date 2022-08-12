package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Lector;
import com.example.demo.model.Prestamo;

public interface PrestamosDao extends JpaRepository<Prestamo, Long>{
	
	@Query("select COUNT(p.id) from Prestamo p where p.lector=?1 group by p.lector")
	public Optional<Integer> numeroPrestamosLector(Lector lector);
	
}
