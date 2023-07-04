package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carteira implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double saldoDisponivel;
	
	public Carteira (double saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}

	
	public long getId() {
		return id;
	}


	public double getSaldoDisponivel() {
		return saldoDisponivel;
	}
	
	

}
