package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="copia")
public class Copia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private EstadoCopia estado;
	
	@ManyToOne
	@JoinColumn(name = "libro_copia", nullable = false, updatable = false)
	private Libro libroCopia;
	
	/**
	 * getters && setters
	 * @return
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoCopia getEstado() {
		return estado;
	}

	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}

	public Libro getLibroCopia() {
		return libroCopia;
	}

	public void setLibroCopia(Libro libroCopia) {
		this.libroCopia = libroCopia;
	}
	
	
	
}
