package com.example.demo;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Prestamo;
import com.example.demo.service.IPrestamosService;

public class HiloMultarDesMultar extends Thread {

	@Autowired
	IPrestamosService prestamosService;

	private String s; // para almacenar los mensajes
	private int pausa; // pausa antes de presentar el mensaje

	// Constructor, se indica el mensaje y la pausa

	public HiloMultarDesMultar(String m, int p) {

		s = m;
		pausa = p;

	}

	public void run() {

		do {
			List<Prestamo> listaPrestamos = prestamosService.getAllPrestamos();
			
			listaPrestamos.stream().map(prestamo -> {
					Long diff = prestamo.getFin().getTime()-prestamo.getInicio().getTime();
				  	TimeUnit time = TimeUnit.DAYS; 
			        long diasDiferencia = time.convert(diff, TimeUnit.MILLISECONDS);
	
			        
				if(diasDiferencia>30) {
					prestamo.getLector();
				}
				return prestamo;
			});
			
			try {
				sleep(pausa * 1000);
			} catch (InterruptedException e) {
				;
			}

			System.out.println(s);

		} while (true);
	}

}
