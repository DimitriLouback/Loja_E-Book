package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Carteira implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private double saldoDisponivel;
	
	@OneToOne(mappedBy="carteira")
	private Cliente cliente;
	
	public Carteira () {
		this.saldoDisponivel = 0;
	}

	public long getId() {
		return id;
	}


	public double getSaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setSaldoDisponivel(double saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}
	

}
