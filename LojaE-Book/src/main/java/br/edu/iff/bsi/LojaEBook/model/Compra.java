package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Compra implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String dataHora;
	private int qtdProdutos;
	private double precoFinal;
	
	public Compra(String dataHora, int qtdProdutos, double precoFinal) {
		super();
		this.dataHora = dataHora;
		this.qtdProdutos = qtdProdutos;
		this.precoFinal = precoFinal;
	}

	public Long getId() {
		return id;
	}

	public String getDataHora() {
		return dataHora;
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}
	
	
	
	
}
