package br.edu.iff.bsi.LojaEBook.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends Pessoa {
	
	@OneToOne
	@JoinColumn(name="fk_carteira")
	private Carteira carteira;
	
	@OneToMany
	@JoinColumn(name="id_cliente")
	private List<Compra> compra;
	
	public Cliente(String nome, String email, String cpf, double saldo) {
		super(nome, email, cpf);
		carteira = new Carteira(saldo);
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public double verSaldo(Carteira carteira) {
		return carteira.getSaldoDisponivel();
	}
	

}
