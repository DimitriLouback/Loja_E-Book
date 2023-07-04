package br.edu.iff.bsi.LojaEBook.model;

import jakarta.persistence.Entity;

@Entity
public class Funcionario extends Pessoa {

	private Cargo cargo;

	public Funcionario(String nome, String email, String cpf, Cargo cargo) {
		super(nome, email, cpf);
		this.cargo = cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}
	
	

}
