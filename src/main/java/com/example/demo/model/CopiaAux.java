package com.example.demo.model;


import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CopiaAux  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idLibro;
	private EstadoCopia estado;
	
	/**
	 * Setters & getters
	 */
	public Long getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}
	public EstadoCopia getEstado() {
		return estado;
	}
	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
