package br.edu.iff.bsi.LojaEBook.model;


import jakarta.persistence.Entity;

@Entity
public class Cliente extends Pessoa {
	
	private Carteira carteira;
	
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
