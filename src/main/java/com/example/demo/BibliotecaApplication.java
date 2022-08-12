package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		
		Thread t1 = new Thread();
		
		t1.start();
		
		
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
