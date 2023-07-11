package br.edu.iff.bsi.LojaEBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Funcionario extends Pessoa {

	@ManyToOne()
	@JoinColumn(name="fk_cargo")
	private Cargo cargo;

	public Funcionario(String nome, String email, String cpf, Cargo cargo) {
		super(nome, email, cpf);
		this.cargo = cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}
	
	

}
