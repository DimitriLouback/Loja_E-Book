package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Telefone implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String numero;

	public Telefone(String numero) {
		super();
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	
	
}
