package br.edu.iff.bsi.LojaEBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Funcionario extends Pessoa {

	@ManyToOne()
	@JoinColumn(name="fk_cargo")
	private Cargo cargo;
	
	
	public Funcionario(String nome, String email, String cpf, String senha, String telefone) {
		super(nome, email, cpf, senha, telefone);
	}
	
	public Funcionario(Long id, String nome, String email, String cpf, String senha, String telefone) {
		super(id, nome, email, cpf, senha, telefone);
	}

	private Funcionario() {}

	public Cargo getCargo() {
		return cargo;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	

}
