package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class PrestamoAux implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCopia;
	private Long idLector;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fin;
	
	/**
	 * getter & setters
	 * @return
	 */

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
	public Long getIdCopia() {
		return idCopia;
	}
	public void setIdCopia(Long idCopia) {
		this.idCopia = idCopia;
	}
	public Long getIdLector() {
		return idLector;
	}
	public void setIdLector(Long idLector) {
		this.idLector = idLector;
	}
	
	
	
}
