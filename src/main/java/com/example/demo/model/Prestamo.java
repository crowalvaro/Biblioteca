package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="prestamo")
public class Prestamo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fin;
	
	@OneToOne
	@JoinColumn(unique=true,nullable=false)
	private Copia copia;
	@OneToOne
	@JoinColumn(name = "lector_id",nullable=false)
	private Lector lector;
	
	
	
	/**
	 * getters & setters
	 * @return
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Copia getCopia() {
		return copia;
	}
	public void setCopia(Copia copia) {
		this.copia = copia;
	}
	public Lector getLector() {
		return lector;
	}
	public void setLector(Lector lector) {
		this.lector = lector;
	}

	
}
