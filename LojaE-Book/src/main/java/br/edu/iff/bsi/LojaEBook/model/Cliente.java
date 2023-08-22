package br.edu.iff.bsi.LojaEBook.model;


import java.util.ArrayList;
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
	
	public Cliente(String nome, String email, String cpf, String senha, String telefone) {
		super(nome, email, cpf, senha, telefone);
		this.compra = new ArrayList();
	}
	
	private Cliente() {}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public double verSaldo() {
		return this.carteira.getSaldoDisponivel();
	}
	
	public void adicionarCompra(Compra compra) {
		this.compra.add(compra);
	}
	
	public void removerCompra(Compra compra) {
		this.compra.remove(compra);
	}
	
	public void adicionarSaldo(double saldo) {
		this.carteira.setSaldoDisponivel(this.carteira.getSaldoDisponivel()+saldo);
	}
	
	public void removerSaldo(double saldo) {
		double retirada = this.carteira.getSaldoDisponivel()-saldo;
		if(retirada>=0) {			
			this.carteira.setSaldoDisponivel(retirada);
		}
	}

}
