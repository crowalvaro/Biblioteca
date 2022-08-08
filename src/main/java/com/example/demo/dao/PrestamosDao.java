package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Prestamo;

public interface PrestamosDao extends JpaRepository<Prestamo, Long>{

}
